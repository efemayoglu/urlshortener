package tapu.urlshortener.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.business.abstracts.LoginService;
import tapu.urlshortener.business.abstracts.UserService;
import tapu.urlshortener.entities.concretes.User;

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
    public void shouldLoginForGeneratedUser(){

        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        var userSaveResult = userService.save(user);

        assertNotNull(userSaveResult);
        assertTrue(userSaveResult.getId() > 0);

        var loginResult = loginService.getByUsernameAndPassword(username, password);

        assertNotNull(loginResult);
        assertTrue(loginResult.isSuccess());
        assertTrue(loginResult.getData().getId() > 0);
    }

    @Test
    public void shouldNotLoginWithEmptyUser(){
        final String username = "";
        final String password = "";

        var result = loginService.getByUsernameAndPassword(username, password);

        assertNull(result.getData());
    }

    @Test
    public void shouldNotLoginWithGeneratedCredentials(){
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        var userSaveResult = userService.save(user);

        assertNotNull(userSaveResult);
        assertTrue(userSaveResult.getId() > 0);

        var loginResult = loginService.getByUsernameAndPassword(username, "123456");
        assertEquals(false, loginResult.isSuccess());
    }
}
