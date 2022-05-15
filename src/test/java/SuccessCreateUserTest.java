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

public class SuccessCreateUserTest {
    UserClient userClient;
    String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();

    }

    @After
    public void tearDown(){
        userClient.delete(accessToken);
    }

    @DisplayName("Check /api/auth/register - success Creating unique User")
    @Test
    public void successCreatingUserTest() throws JsonProcessingException {
        var user = UserGenerator.getRandomEmailPasswordName();
        ValidatableResponse createResponse = userClient.create(user);
        var response = createResponse.statusCode(200).extract().body();
        accessToken = createResponse.extract().path("accessToken");
        assertThat("User cannot created", response, is(notNullValue()));
    }

}
