package tapu.urlshortener.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.business.abstracts.LoginService;
import tapu.urlshortener.business.abstracts.UserService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.LoginRequest;
import tapu.urlshortener.entities.dtos.LoginResponse;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginServiceIntegrationTest {

    private LoginService loginService;
    private UserService userService;


    @Autowired
    public LoginServiceIntegrationTest(LoginService loginService, UserService userService){
        this.loginService = loginService;
        this.userService = userService;
    }

    @Test
    public void itShouldLoginForGeneratedUser(){

        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User userSaveResult = userService.save(user);

        assertNotNull(userSaveResult);
        assertTrue(userSaveResult.getId() > 0);



        DataResult<LoginResponse> loginResult = loginService.getByUsernameAndPassword(new LoginRequest(username, password));

        assertNotNull(loginResult);
        assertTrue(loginResult.isSuccess());
        assertTrue(loginResult.getData().getId() > 0);
    }

    @Test
    public void itShouldNotLoginWithEmptyUser(){
        final String username = "";
        final String password = "";

        DataResult<LoginResponse>  result = loginService.getByUsernameAndPassword(new LoginRequest(username, password));

        assertNull(result.getData());
    }

    @Test
    public void itShouldNotLoginWithGeneratedCredentials(){
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User userSaveResult = userService.save(user);

        assertNotNull(userSaveResult);
        assertTrue(userSaveResult.getId() > 0);

        DataResult<LoginResponse>  loginResult = loginService.getByUsernameAndPassword(new LoginRequest(username, username));
        assertEquals(false, loginResult.isSuccess());
    }
}
