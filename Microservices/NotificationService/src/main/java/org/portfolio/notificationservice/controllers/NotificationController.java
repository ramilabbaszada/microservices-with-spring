package org.portfolio.notificationservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "notification")
@Validated
public class NotificationController
{

    @PostMapping()
    public ResponseEntity getHome(@RequestParam Long id)
    {
        return new ResponseEntity("Notification sent to "+id+" number user",HttpStatus.OK);
    }



}
