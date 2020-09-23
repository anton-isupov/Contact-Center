package com.tinkoff.contact.center.repository;

import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ApplicationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ApplicationRepository applicationRepository;

    @Test
    void findTopByContactIdOrderByCreatedDesc() {
        Contact contact = new Contact();
        Application application1 = new Application("app");
        Application application2 = new Application("app");
        Application application3 = new Application("app");
        contact.addAllApplications(Arrays.asList(
                application1,
                application2,
                application3
        ));

        entityManager.persist(contact);
        entityManager.flush();

        Application app = applicationRepository
                .findTopByContactIdOrderByCreatedDesc(String.valueOf(entityManager.getId(contact)))
                .orElse(null);

        Assertions.assertEquals(app.getProductName(), application3.getProductName());

    }
}
