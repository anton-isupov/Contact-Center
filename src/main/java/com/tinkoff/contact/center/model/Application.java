package com.tinkoff.contact.center.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("Application Model")
public class Application {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="APPLICATION_ID", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    @ApiModelProperty("Id of application in database")
    private String id;

    @Column(name="DT_CREATED")
    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @ApiModelProperty("Created Date of application in database")
    private Date created;

    @Column(name="PRODUCT_NAME")
    @ApiModelProperty("Product Name of application")
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_id")
    @ToString.Exclude
    @JsonIgnore
    @ApiModelProperty("Contact who create this application")
    private Contact contact;

    public Application(String productName) {
        this.productName = productName;
    }
}
