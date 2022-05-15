import com.UserClient;
import com.UserCredentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UnSuccessLoginUserTest {
    UserClient userClient;


    @Before
    public void setUp() {
        userClient = new UserClient();
    }


// ok
    @DisplayName("Check /api/auth/token - UnSuccess User Login With unCorrect Login, Password and Token")
    @Test
    public void UnSuccessUserLoginWithLoginPasswordAndTokenTest() {
        UserCredentials userCredentials = new UserCredentials("test-ssdatga@yandex.ru", "password", "unCorrect", "unCorrect");
        ValidatableResponse createResponse = userClient.login(userCredentials);
        var statusCode = createResponse.extract().statusCode();
        assertThat("Order cannot be created with auth and ingredients", statusCode, equalTo(401));

    }

}

