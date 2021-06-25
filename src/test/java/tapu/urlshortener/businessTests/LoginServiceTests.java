package tapu.urlshortener.businessTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.businessTests.abstracts.LoginService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginServiceTests {

    private LoginService loginService;

    @Autowired
    public LoginServiceTests(LoginService loginService){
        this.loginService = loginService;
    }

    @Test
    public void shouldLoginUser(){
        final String username = "efemayoglu";
        final String password = "123456";

        var result = loginService.getByUsernameAndPassword(username, password);

        assertNotNull(result.getData());
        assertNotNull(result.getData().getId());
        assertTrue(result.getData().getId() != 0);
        assertTrue(result.getData().getId() > 0);
    }

    @Test
    public void shouldNotLoginUser(){
        final String username = "efemayoglu";
        final String password = "1234561";

        var result = loginService.getByUsernameAndPassword(username, password);

        assertNull(result.getData());
    }
}
