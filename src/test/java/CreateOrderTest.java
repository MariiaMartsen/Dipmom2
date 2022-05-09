import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class CreateOrderTest {

    UserClient userClient;
    User user;
    String accessToken;
    OrderClient orderClient;


    @Before
    public void setUp() throws JsonProcessingException {
        userClient = new UserClient();
        user = UserGenerator.getRandomEmailPasswordName();
        var createdUser =  userClient.create(user);
        accessToken =  createdUser.extract().path("accessToken");
        orderClient = new OrderClient();

    }

    @After
    public void tearDown(){
        userClient.delete();
    }


    @DisplayName("Check /api/orders - Success creating order with auth and ingredients")
    @Test
    public void SuccessCreatingOrderTest() throws JsonProcessingException {
        ValidatableResponse createResponse = OrderClient.createWithIngredients(accessToken);
        var statusCode = createResponse.extract().statusCode();
        assertThat("Order cannot be created with auth and ingredients", statusCode, equalTo(200));
    }

    //ок, возможно создание без авторизации
    @DisplayName("Check /api/orders - unSuccess creating order without auth")
    @Test
    public void unSuccessCreatingOrderWithoutAuthTest() throws JsonProcessingException {
        ValidatableResponse createResponse = OrderClient.createWithoutAuth();
        var statusCode = createResponse.extract().statusCode();
        assertThat("Order can be created without auth", statusCode, equalTo(401));
    }


    @DisplayName("Check /api/orders - unSuccess creating order without ingredients")
    @Test
    public void unSuccessCreatingOrderWithoutIngredientsTest() throws JsonProcessingException {
        ValidatableResponse createResponse = OrderClient.createWithoutIngredients(accessToken);
        var statusCode = createResponse.extract().statusCode();
        assertThat("User cannot be created", statusCode, equalTo(400));
    }


    @DisplayName("Check /api/orders - unSuccess creating order with bad ingredients")
    @Test
    public void unSuccessCreatingOrderWithBadIngredientsTest() throws JsonProcessingException {
        ValidatableResponse createResponse = OrderClient.createWithBadIngredients(accessToken);
        var statusCode = createResponse.extract().statusCode();
        assertThat("User cannot be created", statusCode, equalTo(500));
    }

}
