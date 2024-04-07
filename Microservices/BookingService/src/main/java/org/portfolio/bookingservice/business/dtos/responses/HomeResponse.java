package org.portfolio.bookingservice.business.dtos.responses;

import java.util.List;

public class HomeResponse {
    private String city;
    private String street;
    private Integer bed;
    private String bath;
    private Integer sqft;
    private Integer price;
    private List<ImageResponse> images;
}
