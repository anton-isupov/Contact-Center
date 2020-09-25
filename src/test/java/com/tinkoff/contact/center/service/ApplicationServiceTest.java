package com.tinkoff.contact.center.service;

import com.tinkoff.contact.center.exception.NoSuchApplicationException;
import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.model.Contact;
import com.tinkoff.contact.center.repository.ApplicationRepository;
import com.tinkoff.contact.center.repository.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;

    @MockBean
    private ApplicationRepository applicationRepository;

    @Test
    void findTopByContactIdOrderByCreatedDesc() throws NoSuchApplicationException {
        Application a = new Application();
        a.setProductName("app");

        Mockito.doReturn(Optional.of(a))
                .when(applicationRepository)
                .findTopByContactIdOrderByCreatedDesc("uuid");

        Application uuid = this.applicationService.findTopByContactIdOrderByCreatedDesc("uuid");
        assertEquals(uuid.getProductName(), "app");
    }

    @Test
    void findTopByContactIdOrderByCreatedDescNullCheck() {
        NoSuchApplicationException noSuchApplicationException = assertThrows(NoSuchApplicationException.class,
                () -> this.applicationService.findTopByContactIdOrderByCreatedDesc(null));
        assertEquals(noSuchApplicationException.getMessage(), "Contact id can not be null");
    }

    @Test
    void findTopByContactIdOrderByCreatedDescIdNotFoundCheck() {
        NoSuchApplicationException noSuchApplicationException = assertThrows(NoSuchApplicationException.class,
                () -> this.applicationService.findTopByContactIdOrderByCreatedDesc("0000"));
        assertEquals(noSuchApplicationException.getMessage(), "Could not find any application");
    }

}
