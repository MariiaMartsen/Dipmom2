import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends BurgerRestClient {
    private static final String USER_PATH = "/api/auth/";

    @Step("Create user")
    public ValidatableResponse create(User user) throws JsonProcessingException {

        String result = new ObjectMapper().writeValueAsString(user);

        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .and()
                .body(result)
                //.log().all()
                .when()
                .post(USER_PATH + "register")
                .then();

    }

    @Step("Login user")
    public ValidatableResponse login(UserCredentials userCredentials) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", userCredentials.getAccessToken())
                .body(userCredentials)
                //.log().all()
                .when()
                .post(USER_PATH + "login")
                .then();

    }

    @Step("Change user with auth")
    public ValidatableResponse change(String accessToken) {

        User user = new User(UserGenerator.userEmail, UserGenerator.userPassword, UserGenerator.userName);

        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(user)
                //.log().all()
                .and()
                .when()
                .patch(USER_PATH + "user")
                .then();
    }

    @Step("Change user without auth")
    public ValidatableResponse change() {

        User user = new User(UserGenerator.userEmail, UserGenerator.userPassword, UserGenerator.userName);

        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .body(user)
                .and()
                .when()
                .patch(USER_PATH + "user")
                .then();
    }

    @Step("Logout user")
    public ValidatableResponse logout(String token) {
        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .body(token)
                .and()
                .when()
                .post(USER_PATH + "logout")
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse delete() {

        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .when()
                .delete(USER_PATH + "user")
                .then();

    }

}
