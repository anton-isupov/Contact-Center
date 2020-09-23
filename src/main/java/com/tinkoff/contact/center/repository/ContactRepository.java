package com.tinkoff.contact.center.repository;

import com.tinkoff.contact.center.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
}
