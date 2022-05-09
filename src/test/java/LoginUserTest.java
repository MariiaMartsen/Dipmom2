import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginUserTest {
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
        userClient.delete();
    }

    @DisplayName("Check /api/auth/token - success User Login With Login, Password and Token") //как сделать залогин
    @Test
    public void successUserLoginWithLoginPasswordAndTokenTest() {
        var cred = new UserCredentials(user.email, user.password, accessToken, token);
        ValidatableResponse loginResponse = userClient.login(cred);
        var response = loginResponse.statusCode(200).extract().body();
        assertThat("User cannot be logged in", loginResponse, Matchers.is(notNullValue()));

    }

    @DisplayName("Check /api/auth/token - UnSuccess User Login With unCorrect Login, Password and Token") //как сделать залогин
    @Test
    public void UnSuccessUserLoginWithLoginPasswordAndTokenTest() {
        UserCredentials userCredentials = new UserCredentials("test-ssdatga@yandex.ru", "password", "", "");
        ValidatableResponse createResponse = userClient.login(userCredentials);
        var response = createResponse.statusCode(401).extract().body();
        assertThat("User can be logged in with unCorrect Login, Password and Token", response, Matchers.is(notNullValue()));

    }

}
