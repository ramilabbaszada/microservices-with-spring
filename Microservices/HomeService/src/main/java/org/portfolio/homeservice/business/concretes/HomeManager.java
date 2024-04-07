package org.portfolio.homeservice.business.concretes;

import io.swagger.v3.core.util.ReflectionUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.portfolio.homeservice.business.abstracts.HomeService;
import org.portfolio.homeservice.business.dtos.requests.HomeRequest;
import org.portfolio.homeservice.business.dtos.responses.HomeResponse;
import org.portfolio.homeservice.business.messages.Messages;
import org.portfolio.homeservice.business.rules.HomeRules;
import org.portfolio.homeservice.configuration.mappers.CustomMapper;
import org.portfolio.homeservice.entities.Home;
import org.portfolio.homeservice.entities.Image;
import org.portfolio.homeservice.repositories.FileSystemRepository;
import org.portfolio.homeservice.repositories.sqlRepositories.HomeRepository;
import org.portfolio.homeservice.repositories.sqlRepositories.ImageRepository;
import org.portfolio.homeservice.utils.exceptions.BusinessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class HomeManager implements HomeService
{
    private FileSystemRepository fileSystemRepository;
    private HomeRepository homeRepository;
    private ImageRepository imageRepository;

    @Override
    public HomeResponse getHomeById(Long id)
    {

        HomeRules.checkIfIdIsValid(id);
        Optional<Home> optionalHome=homeRepository.findById(id);

        return optionalHome.map(CustomMapper::getHomeResponseFromHome).orElse(null);
    }

    @Override
    public List<HomeResponse> getPageableHomes(Integer pageNumber, Integer pageSize)
    {
        HomeRules.checkPageNumberAndPageSize(pageNumber,pageSize);
        return homeRepository.findAll(PageRequest.of(pageNumber,pageSize)).map(CustomMapper::getHomeResponseFromHome).getContent();
    }


    @Transactional
    @Override
    @SneakyThrows
    public HomeResponse addHome(HomeRequest addHomeRequest)
    {
        Home home=CustomMapper.getHomeFromHomeRequest(addHomeRequest);
        home=homeRepository.save(home);
        createImages(home.getImages());
        return CustomMapper.getHomeResponseFromHome(home);
    }

    @Transactional
    @Override
    public HomeResponse updateHome(Long id, HomeRequest updateHomeRequest)
    {
        HomeRules.checkIfIdIsValid(id);
        Optional<Home> optionalHome=homeRepository.findById(id);
        if(optionalHome.isEmpty())
        {
            throw new BusinessException(Messages.invalidIdException);
        }
        Home home=optionalHome.get();

        for (Image image : home.getImages())
        {
            fileSystemRepository.deleteFile(image.getFileName());
        }
        home=CustomMapper.getHomeFromHomeRequest(updateHomeRequest);
        home.setId(id);
        imageRepository.deleteByHomeId(id);
        homeRepository.save(home);
        createImages(home.getImages());
        return CustomMapper.getHomeResponseFromHome(homeRepository.findById(id).get());
    }

    @Transactional
    @SneakyThrows
    @Override
    public HomeResponse updateHomePartially(Long id, Map<String, Object> fields)
    {
        HomeRules.checkIfIdIsValid(id);
        Optional<Home> optionalHome=homeRepository.findById(id);

        if(optionalHome.isEmpty())
        {
            throw new BusinessException(Messages.invalidIdException);
        }

        Home home=optionalHome.get();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Field field = ReflectionUtils.findField(key, Home.class);
            field.setAccessible(true);
            field.set(home, value);
        }
        homeRepository.save(home);
        return CustomMapper.getHomeResponseFromHome(homeRepository.findById(id).get());
    }


    @Transactional
    @Override
    public void deleteHome(Long id)
    {
        HomeRules.checkIfIdIsValid(id);
        Optional<Home> optionalHome=homeRepository.findById(id);

        if(optionalHome.isEmpty())
        {
            throw new BusinessException(Messages.invalidIdException);
        }

        for (Image image : optionalHome.get().getImages())
        {
            fileSystemRepository.deleteFile(image.getFileName());
        }
        homeRepository.deleteById(id);
    }


    private void createImages(List<Image> images)
    {
        try
        {
            for (Image image : images)
            {
                fileSystemRepository.createFile(image.getFileName(),image.getImageData());
            }
        }catch (Exception exception)
        {
            for (Image image : images)
            {
                fileSystemRepository.deleteFile(image.getFileName());
            }
            throw exception;
        }
    }
}
