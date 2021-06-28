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
import org.springframework.test.web.servlet.ResultActions;
import tapu.urlshortener.business.abstracts.LoginService;
import tapu.urlshortener.business.abstracts.UserService;
import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.entities.dtos.UserCreateRequest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UsersController.class)
public class UsersControllerTest {
    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;


    @Test
    void whenCalledValidUser_itShouldReturnHTTP200() throws Exception{

        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();

        UserCreateRequest request = new UserCreateRequest();
        request.setUsername(username);
        request.setPassword(password);

        ResultActions actions = mockMvc.perform(post("/api/users/add")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(request)));

        ArgumentCaptor<UserCreateRequest> captor = ArgumentCaptor.forClass(UserCreateRequest.class);
        verify(userService, times(1)).save(captor.capture());
        assertThat(captor.getValue().getUsername()).isEqualTo(username);
        assertThat(captor.getValue().getPassword()).isEqualTo(password);
        actions.andExpect(status().isOk());


    }
}