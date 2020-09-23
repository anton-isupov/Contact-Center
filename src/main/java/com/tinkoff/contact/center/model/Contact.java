package com.tinkoff.contact.center.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="CONTACT")
@NoArgsConstructor
@Data
public class Contact {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="CONTACT_ID")
    private String id;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Application> applications = new ArrayList<>();

    public void addAllApplications(Collection<Application> applications) {
        this.applications.addAll(applications);
        applications
                .forEach(application -> application.setContact(this));
    }

    public void addApplication(Application application) {
        this.applications.add(application);
        application.setContact(this);
    }


    public void removeApplication(Application application) {
        this.applications.remove(application);
        application.setContact(null);
    }
}
