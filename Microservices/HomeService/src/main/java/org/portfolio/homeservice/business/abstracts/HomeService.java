package org.portfolio.homeservice.business.abstracts;
import org.portfolio.homeservice.business.dtos.requests.HomeRequest;
import org.portfolio.homeservice.business.dtos.responses.HomeResponse;

import java.util.List;
import java.util.Map;

public interface HomeService {
    HomeResponse getHomeById(Long id);
    List<HomeResponse> getPageableHomes(Integer pageNumber, Integer pageSize);
    HomeResponse addHome(HomeRequest addHomeRequest);
    HomeResponse updateHome(Long id,HomeRequest addHomeRequest);
    HomeResponse updateHomePartially(Long id, Map<String,Object> fields);
    void deleteHome(Long id);
}
