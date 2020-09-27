package com.tinkoff.contact.center.controller;

import com.tinkoff.contact.center.response.ApplicationResponse;
import com.tinkoff.contact.center.response.Response;
import com.tinkoff.contact.center.response.status.ResponseStatus;
import com.tinkoff.contact.center.service.ContactCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest/v1/contact/center")
@Api("Application Resources")
public class ApplicationController {

    private final ContactCenterService contactCenterService;

    @Autowired
    public ApplicationController(ContactCenterService contactCenterService) {
        this.contactCenterService = contactCenterService;
    }

    @GetMapping("/getLastApplication")
    @ApiOperation(value = "Get Last Application By Contact Id", response = ApplicationResponse.class)
    public ResponseEntity<Response> getLastApplicationByContactId(@RequestParam("id") String id) {
        return this.contactCenterService.getLastApplicationByContactId(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handleError() {
        return new ResponseEntity<>(
                new Response(ResponseStatus.FAILURE, "There was an unexpected error. Try again later"),
                HttpStatus.OK
        );
    }

}
