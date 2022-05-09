import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderClient extends BurgerRestClient {
    private static final String ORDER_PATH = "/api/orders";

    @Step("Create order with auth and ingredients")
    public static ValidatableResponse createWithIngredients(String accessToken) throws JsonProcessingException {

        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body("{\n" +
                        "\"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa70\"]\n" +
                        "}")
                .log().all()
                .when()
                .post(ORDER_PATH)
                .then();

    }

    @Step("Create order without auth")
    public static ValidatableResponse createWithoutAuth() throws JsonProcessingException {

        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "\"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\",\"61c0c5a71d1f82001bdaaa70\"]\n" +
                        "}")
                .log().all()
                .when()
                .post(ORDER_PATH)
                .then();

    }

    @Step("Create order with auth and without ingredients")
    public static ValidatableResponse createWithoutIngredients(String accessToken) throws JsonProcessingException {

        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .log().all()
                .when()
                .post(ORDER_PATH)
                .then();

    }

    @Step("Create order with auth and with unCorrect ingredients")
    public static ValidatableResponse createWithBadIngredients(String accessToken) throws JsonProcessingException {

        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body("{\n" +
                        "\"ingredients\": [\"first bad\",\"second bad\"]\n" +
                        "}")
                .log().all()
                .when()
                .post(ORDER_PATH)
                .then();

    }

    @Step("Get orders list with auth")
    public static ValidatableResponse getOrdersWithAuth(String accessToken){

        return given()
                .spec(getBaseSpec())
                .header("Authorization", accessToken)
                .when()
                .get(ORDER_PATH)
                .then();

    }

    @Step("Get orders list without auth")
    public static ValidatableResponse getOrdersWithoutAuth(){
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();

    }


}
