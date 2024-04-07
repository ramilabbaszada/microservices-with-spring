package org.portfolio.homeservice.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class HomeRequest {
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String street;
    @NotNull
    private Integer bed;
    @NotNull
    @NotBlank
    private String bath;
    @NotNull
    private Integer sqft;
    @NotNull
    private Integer price;
    @NotNull
    private List<MultipartFile> images;
}
