import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChangeUserTest {
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
        userClient.delete();
    }

    @Test
    @DisplayName("Check /api/auth/user - success changing User with auth")
    public void successChangingUserTest() {
        ValidatableResponse createResponse = userClient.change(accessToken);
        var response = createResponse.statusCode(200).extract().body();
        assertThat("User cannot be changed", response, is(notNullValue()));
    }

    @Test
    @DisplayName("Check /api/auth/user - UnSuccess changing User without auth")
    public void UnSuccessChangingUserTest() {
        ValidatableResponse createResponse = userClient.change();
        var response = createResponse.statusCode(401).extract().body();
        assertThat("User can be changed", response, is(notNullValue())); //
    }

}
