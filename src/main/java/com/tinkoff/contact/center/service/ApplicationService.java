package com.tinkoff.contact.center.service;

import com.tinkoff.contact.center.exception.NoSuchApplicationException;
import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application findTopByContactIdOrderByCreatedDesc(String contactId) throws NoSuchApplicationException {
        if (contactId == null)
            throw new NoSuchApplicationException("Contact id can not be null");

        return this.applicationRepository.findTopByContactIdOrderByCreatedDesc(contactId)
                .orElseThrow(() -> new NoSuchApplicationException("Could not find any application"));
    }

}
