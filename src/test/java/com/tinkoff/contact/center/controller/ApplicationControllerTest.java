package com.tinkoff.contact.center.controller;

import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.response.ApplicationResponse;
import com.tinkoff.contact.center.response.Response;
import com.tinkoff.contact.center.service.ContactCenterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactCenterService contactCenterService;

    @Test
    void getLastApplicationByContactId() throws Exception {
        Application a = new Application();
        a.setProductName("app");
        ResponseEntity<Response> r = new ResponseEntity<>(
                new ApplicationResponse("1", a),
                HttpStatus.OK
        );

        given(contactCenterService.getLastApplicationByContactId("1")).willReturn(r);

        mvc.perform(get("/api/rest/v1/contact/center/getLastApplication/1")
                .contentType(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/api/rest/v1/contact/center/getLastApplication/0")
                .contentType(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void handleError() {
    }
}
