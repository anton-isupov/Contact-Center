package com.tinkoff.contact.center.repository;

import com.tinkoff.contact.center.model.Application;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, String> {

    List<Application> findByContactIdOrderByCreatedDesc(String contactId);

    Optional<Application> findTopByContactIdOrderByCreatedDesc(String contactId);

}
