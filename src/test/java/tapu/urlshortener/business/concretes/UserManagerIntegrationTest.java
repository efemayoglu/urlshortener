package tapu.urlshortener.business.concretes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.entities.dtos.UserCreateRequest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserManagerIntegrationTest {

    @Autowired
    private UserManager userManager;

    @Test
    void whenCalledRegisterWithExistUser_itShouldErrorResult() {

        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        UserCreateRequest request = new UserCreateRequest(
            username,password
        );

        Result addedUserResult = userManager.save(request);

        assertNotNull(addedUserResult);
        assertTrue(addedUserResult.isSuccess());

        Result addedUserResult2 = userManager.save(request);

        assertNotNull(addedUserResult2);
        assertFalse(addedUserResult2.isSuccess());



    }
}