package com.tinkoff.contact.center.loader;

import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.model.Contact;
import com.tinkoff.contact.center.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    private final ContactRepository contactRepository;

    @Autowired
    public DataLoader(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void run(ApplicationArguments args) {
        Contact contact1 = new Contact();
        contact1.addAllApplications(Arrays.asList(
                new Application("app1"),
                new Application("app2"),
                new Application("app3"),
                new Application("app4")
        ));

        Contact contact2 = new Contact();
        contact2.addAllApplications(Arrays.asList(
                new Application("app21"),
                new Application("app22"),
                new Application("app23"),
                new Application("app24"),
                new Application("app25")
        ));

        contactRepository.saveAll(Arrays.asList(
                contact1,
                contact2
        ));

    }
}
