package org.portfolio.homeservice.controllers;

import lombok.AllArgsConstructor;
import org.portfolio.homeservice.business.abstracts.HomeService;
import org.portfolio.homeservice.business.dtos.requests.HomeRequest;
import org.portfolio.homeservice.business.dtos.responses.HomeResponse;
import org.portfolio.homeservice.business.messages.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "homes")
@Validated
public class HomeController
{

    private HomeService homeService;
    @GetMapping("{id}")
    public HomeResponse getHome(@PathVariable Long id)
    {
        HomeResponse homeResponse=homeService.getHomeById(id);
        return homeResponse;
    }

    /*@GetMapping()
    public ResponseEntity<List<HomeResponse>> getPageableHomes(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(homeService.getPageableHomes(pageNumber,pageSize),HttpStatus.OK);
    }*/

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HomeResponse> addHome(@ModelAttribute HomeRequest addHomeRequest){
        return new ResponseEntity<>(homeService.addHome(addHomeRequest),HttpStatus.OK);
    }
    @PutMapping(value = "{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HomeResponse> updateHome(@PathVariable Long id ,@ModelAttribute HomeRequest updateHomeRequest){
        return new ResponseEntity<>(homeService.updateHome(id,updateHomeRequest),HttpStatus.OK);
    }

    @PatchMapping(value="{id}")
    public ResponseEntity<HomeResponse> updateHomePartially(@PathVariable Long id ,@RequestBody Map<String, Object> fields){
        return new ResponseEntity<>(homeService.updateHomePartially(id,fields),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHome(@PathVariable Long id){
        homeService.deleteHome(id);
        return new ResponseEntity<>(Messages.successfullyDeleted,HttpStatus.OK);
    }

}
