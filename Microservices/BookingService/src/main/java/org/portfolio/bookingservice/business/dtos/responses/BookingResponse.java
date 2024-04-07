package org.portfolio.bookingservice.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long userId;
    private Long homeId;
    private LocalDateTime starts;
    private LocalDateTime ends;
    private Integer price;
    private String additionalNotes;
}
