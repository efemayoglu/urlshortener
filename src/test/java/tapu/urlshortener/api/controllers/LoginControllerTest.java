package tapu.urlshortener.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import tapu.urlshortener.business.abstracts.LoginService;
import tapu.urlshortener.core.utilities.results.ErrorDataResult;
import tapu.urlshortener.entities.dtos.LoginRequest;
import tapu.urlshortener.entities.dtos.UserCreateRequest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoginController.class)
class LoginControllerTest {

    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoginService loginService;

    @Test
    void whenInValidInput_thenReturns200()  throws Exception{
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();

        LoginRequest request = new LoginRequest(username, password);

        ResultActions actions = mockMvc.perform(post("/api/login")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(request)));

        ArgumentCaptor<LoginRequest> captor = ArgumentCaptor.forClass(LoginRequest.class);
        verify(loginService, times(1))
                .getByUsernameAndPassword(captor.capture());
        assertThat(captor.getValue().getUsername()).isEqualTo(username);
        assertThat(captor.getValue().getPassword()).isEqualTo(password);
        actions.andExpect(status().isOk());

    }
    @Test
    void whenInValidInput_thenReturns400()  throws Exception{
        String username = "";
        String password = "";

        LoginRequest request = new LoginRequest(username, password);

        ResultActions actions = mockMvc.perform(post("/api/login")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(request)));

        actions.andExpect(status().isBadRequest());

    }
    @Test
    void whenInValidInput_thenReturnErrorMessages()  throws Exception {
        String username = "";
        String password = "";

        LoginRequest request = new LoginRequest(username, password);

        MvcResult mvcResult = mockMvc.perform(post("/api/login")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(request))).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        ErrorDataResult dataResult = objectMapper.readValue(responseBody, ErrorDataResult.class);

        assertNotNull(dataResult.getData());
        assertEquals(false, dataResult.isSuccess());

    }

        @Test
    void handleValidationException(){


    }
}