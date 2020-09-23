package com.tinkoff.contact.center.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinkoff.contact.center.response.status.ResponseStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
    @JsonProperty("status")
    @XmlElement(name="status")
    protected ResponseStatus status;
    @JsonProperty("message")
    @XmlElement(name="message")
    protected String message;

    public Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
