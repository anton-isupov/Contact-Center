package com.tinkoff.contact.center.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/insert-contact.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-contact.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void controllerIntegrationTest() throws Exception {
        this.mockMvc.perform(get("/api/rest/v1/contact/center/getLastApplication?id=uuid"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactId", equalTo("uuid")))
                .andExpect(jsonPath("$.applicationId", equalTo("3")));

    }
}
