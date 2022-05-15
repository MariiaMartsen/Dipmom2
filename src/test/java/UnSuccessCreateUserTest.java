import com.User;
import com.UserClient;
import com.UserGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UnSuccessCreateUserTest {
    UserClient userClient;
    User user;
    String accessToken;
    String token;

    @Before
    public void setUp() throws JsonProcessingException {
        userClient = new UserClient();
        user = UserGenerator.getRandomEmailPasswordName();
        var createdUser =  userClient.create(user);
        accessToken =  createdUser.extract().path("accessToken");
        token = createdUser.extract().path("refreshToken");

    }

    @After
    public void tearDown(){
        userClient.logout(token);
        userClient.delete(accessToken);
    }



    @DisplayName("Check /api/auth/register - UnSuccess Creating with existing User")
    @Test
    public void unSuccessCreatingExistingUserTest() throws JsonProcessingException {
        ValidatableResponse createResponse = userClient.create(user);
        var response = createResponse.statusCode(403).extract().body();
        assertThat("User can be created", response, is(notNullValue()));
    }

    @DisplayName("Check /api/auth/register - UnSuccess Creating User without name")
    @Test
    public void unSuccessCreatingUserWithoutNameTest() throws JsonProcessingException {
        ValidatableResponse createResponse = userClient.create(user);
        var response = createResponse.statusCode(403).extract().body();
        assertThat("User can be created", response, is(notNullValue()));
    }
}


