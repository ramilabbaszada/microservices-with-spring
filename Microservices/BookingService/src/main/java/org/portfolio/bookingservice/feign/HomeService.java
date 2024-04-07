package org.portfolio.bookingservice.feign;

import org.portfolio.bookingservice.business.dtos.responses.HomeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("HOME-SERVICE")
public interface HomeService {
    @GetMapping("homes")
    public HomeResponse getHome(@RequestParam Long id);
}
