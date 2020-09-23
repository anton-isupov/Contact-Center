package com.tinkoff.contact.center.repository;

import com.tinkoff.contact.center.model.Application;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, String> {

    Optional<Application> findTopByContactIdOrderByCreatedDesc(String contactId);

}
