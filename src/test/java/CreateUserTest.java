import com.fasterxml.jackson.core.JsonProcessingException;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateUserTest {
    UserClient userClient;

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @After
    public void tearDown(){
        userClient.delete();
    }

    @DisplayName("Check /api/auth/register - success Creating unique User")
    @Test
    public void successCreatingUserTest() throws JsonProcessingException {
        var us = UserGenerator.getRandomEmailPasswordName();
        ValidatableResponse createResponse = userClient.create(us);
        var response = createResponse.statusCode(200).extract().body();
        assertThat("User cannot created", response, is(notNullValue()));
    }

    @DisplayName("Check /api/auth/register - UnSuccess Creating with existing User")
    @Test
    public void unSuccessCreatingExistingUserTest() throws JsonProcessingException {
        User user = new User("test-data@yandex.ru", "password", "Username");
        ValidatableResponse createResponse = userClient.create(user);
        var response = createResponse.statusCode(403).extract().body();
        assertThat("User can be created", response, is(notNullValue()));
    }

    @DisplayName("Check /api/auth/register - UnSuccess Creating User without name")
    @Test
    public void unSuccessCreatingUserWithoutNameTest() throws JsonProcessingException {
        var us = UserGenerator.getRandomEmailPasswordWithoutName();
        ValidatableResponse createResponse = userClient.create(us);
        var response = createResponse.statusCode(403).extract().body();
        assertThat("User can be created", response, is(notNullValue()));
    }
}
