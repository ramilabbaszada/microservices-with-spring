package org.portfolio.bookingservice.business.abstracts;

import org.portfolio.bookingservice.business.dtos.responses.BookingResponse;

import java.time.LocalDateTime;

public interface BookingService {
    void bookHome(Long id);
}
