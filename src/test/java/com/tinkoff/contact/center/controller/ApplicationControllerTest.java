package com.tinkoff.contact.center.controller;

import com.tinkoff.contact.center.model.Application;
import com.tinkoff.contact.center.response.ApplicationResponse;
import com.tinkoff.contact.center.response.Response;
import com.tinkoff.contact.center.response.status.ResponseStatus;
import com.tinkoff.contact.center.service.ContactCenterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

        mockMvc.perform(get("/api/rest/v1/contact/center/getLastApplication?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactId", equalTo("1")))
                .andExpect(jsonPath("$.productName", equalTo("app")))
                .andExpect(jsonPath("$.status", equalTo("SUCCESS")))
                .andExpect(jsonPath("$.message", equalTo("")))
                .andExpect(jsonPath("$.applicationId", equalTo(a.getId())))
                .andExpect(jsonPath("$.created", equalTo(a.getCreated())));
    }

    @Test
    void getLastApplicationByContactIdNotFound() throws Exception {
        given(contactCenterService.getLastApplicationByContactId("2")).willReturn(
                new ResponseEntity<>(
                        new Response(ResponseStatus.FAILURE, "Could not find any application"),
                        HttpStatus.NOT_FOUND
                )
        );

        mockMvc.perform(get("/api/rest/v1/contact/center/getLastApplication?id=2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", equalTo("FAILURE")))
                .andExpect(jsonPath("$.message", equalTo("Could not find any application")));
    }

}
