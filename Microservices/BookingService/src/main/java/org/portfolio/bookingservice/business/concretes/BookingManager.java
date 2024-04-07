package org.portfolio.bookingservice.business.concretes;


import lombok.AllArgsConstructor;
import org.portfolio.bookingservice.business.abstracts.BookingService;
import org.portfolio.bookingservice.business.dtos.responses.HomeResponse;
import org.portfolio.bookingservice.feign.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service()
@AllArgsConstructor
public class BookingManager implements BookingService
{
    private final HomeService homeService;
    @Override
    public void bookHome(Long id) {

        HomeResponse homeResponse=homeService.getHome(1L);

        if(homeResponse!=null)
            System.out.println(homeResponse+" is booked");
    }
}
