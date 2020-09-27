package com.tinkoff.contact.center.unit.service;

import com.tinkoff.contact.center.exception.NoSuchApplicationException;
import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.repository.ApplicationRepository;
import com.tinkoff.contact.center.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationServiceTest {

    @MockBean
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @Test
    void findTopByContactIdOrderByCreatedDesc() throws NoSuchApplicationException {
        Application a = new Application();
        a.setProductName("app");

        when(applicationRepository.findTopByContactIdOrderByCreatedDesc("uuid"))
                .thenReturn(Optional.of(a));

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
