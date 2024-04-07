package org.portfolio.homeservice.configuration.mappers;

import lombok.SneakyThrows;
import org.portfolio.homeservice.business.dtos.requests.HomeRequest;
import org.portfolio.homeservice.business.dtos.responses.HomeResponse;
import org.portfolio.homeservice.business.dtos.responses.ImageResponse;
import org.portfolio.homeservice.entities.Home;
import org.portfolio.homeservice.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class CustomMapper {
    public static HomeResponse getHomeResponseFromHome(Home home){
        if ( home == null )
            return null;

        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setCity(home.getCity());
        homeResponse.setStreet(home.getStreet());
        homeResponse.setBed(home.getBed());
        homeResponse.setBath(home.getBath());
        homeResponse.setPrice(home.getPrice());
        homeResponse.setSqft(home.getSqft());
        homeResponse.setImages(home.getImages().stream().map(CustomMapper::getImageResponseFromImage).toList());
        return homeResponse;
    }
    public static ImageResponse getImageResponseFromImage(Image image){
        if ( image == null )
            return null;

        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setImageData(image.getImageData());
        imageResponse.setFileName(image.getFileName());
        return imageResponse;
    }
    public static Home getHomeFromHomeRequest(HomeRequest homeRequest){
        Home home=new Home();
        home.setPrice(homeRequest.getPrice());
        home.setBath(homeRequest.getBath());
        home.setBed(homeRequest.getBed());
        home.setSqft(homeRequest.getSqft());
        home.setCity(homeRequest.getCity());
        home.setStreet(homeRequest.getStreet());
        home.setImages(homeRequest.getImages().stream().map(image->getImageFromMultipartFile(image,home)).toList());
        return home;
    }
    @SneakyThrows
    static Image getImageFromMultipartFile(MultipartFile multipartFile, Home home){
        String imageName="img_"+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20)+"."+multipartFile.getContentType().split("/")[1];
        Image image=new Image();
        image.setImageData(multipartFile.getBytes());
        image.setFileName(imageName);
        image.setHome(home);
        return image;
    }

}