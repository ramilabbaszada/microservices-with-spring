package org.portfolio.homeservice.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponse {
    private String city;
    private String street;
    private Integer bed;
    private String bath;
    private Integer sqft;
    private Integer price;
    private List<ImageResponse> images;
}
