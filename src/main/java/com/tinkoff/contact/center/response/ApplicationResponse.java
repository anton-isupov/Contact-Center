package com.tinkoff.contact.center.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.response.status.ResponseStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationResponse extends Response {

    @JsonProperty("contactId")
    @XmlElement(name="contactId")
    private String contactId;
    @JsonProperty("applicationId")
    @XmlElement(name="applicationId")
    private String applicationId;
    @JsonProperty("created")
    @XmlElement(name="created")
    private Date created;
    @JsonProperty("productName")
    @XmlElement(name="productName")
    private String productName;

    public ApplicationResponse(String contactId, Application application) {
        super(ResponseStatus.SUCCESS, "");
        this.contactId = contactId;
        this.applicationId = application.getId();
        this.created = application.getCreated();
        this.productName = application.getProductName();
    }
}
