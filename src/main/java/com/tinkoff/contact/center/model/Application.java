package com.tinkoff.contact.center.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="APPLICATION")
@NoArgsConstructor
@Data
public class Application {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="APPLICATION_ID", updatable = false, nullable = false)
    private String id;

    @Column(name="DT_CREATED")
    @CreationTimestamp
    private Date created;

    @Column(name="PRODUCT_NAME")
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_id")
    @ToString.Exclude
    @JsonIgnore
    private Contact contact;

    public Application(String productName) {
        this.productName = productName;
    }
}
