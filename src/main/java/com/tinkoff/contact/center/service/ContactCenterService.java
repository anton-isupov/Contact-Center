package com.tinkoff.contact.center.service;

import com.tinkoff.contact.center.exception.NoSuchApplicationException;
import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.response.ApplicationResponse;
import com.tinkoff.contact.center.response.Response;
import com.tinkoff.contact.center.response.status.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactCenterService {

    private final ApplicationService applicationService;

    @Autowired
    public ContactCenterService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    public ResponseEntity<Response> getLastApplicationByContactId(String contactId) {
        try {
            Application result = this.applicationService.findTopByContactIdOrderByCreatedDesc(contactId);
            return new ResponseEntity<>(
                    new ApplicationResponse(contactId, result),
                    HttpStatus.OK
            );
        } catch (NoSuchApplicationException e) {
            return new ResponseEntity<>(
                    new Response(ResponseStatus.FAILURE, e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }


}
