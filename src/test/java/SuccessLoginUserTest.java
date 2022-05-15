import com.User;
import com.UserClient;
import com.UserCredentials;
import com.UserGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class SuccessLoginUserTest {
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

    @DisplayName("Check /api/auth/token - success User Login With Login, Password and Token") //как сделать залогин
    @Test
    public void successUserLoginWithLoginPasswordAndTokenTest() {
        var cred = new UserCredentials(user.getEmail(), user.getPassword(), accessToken, token);
        ValidatableResponse loginResponse = userClient.login(cred);
        var response = loginResponse.statusCode(200).extract().body();
        assertThat("User cannot be logged in", loginResponse, Matchers.is(notNullValue()));

    }


}
