package org.portfolio.bookingservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import org.portfolio.bookingservice.business.abstracts.BookingService;
import org.portfolio.bookingservice.kafka.events.HomeBookedEvent;
import org.portfolio.bookingservice.kafka.producers.BookingProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping(value = "booking")
@Validated
public class BookingController
{

    private BookingService bookingService;
    private BookingProducer bookingProducer;

    @CircuitBreaker(name = "home", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "home")
    @Retry(name = "home")
    @PostMapping("/{id}")
    public CompletableFuture<ResponseEntity<String>> cancelBookingByHomeId(@PathVariable Long id){
        return CompletableFuture.supplyAsync(()->{
            //bookingService.bookHome(id);
            bookingProducer.sendMessage(new HomeBookedEvent(Math.toIntExact(id)));
            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
        });
    }

    public CompletableFuture<ResponseEntity<String>> fallbackMethod(@PathVariable Long id, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()->new ResponseEntity<>(runtimeException.getMessage(),HttpStatus.EXPECTATION_FAILED));
    }

}
