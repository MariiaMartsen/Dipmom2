import com.OrderClient;
import com.User;
import com.UserClient;
import com.UserGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GetOrdersTest {
    UserClient userClient;
    OrderClient orderClient;
    User user;
    String accessToken;

    @Before
    public void setUp() throws JsonProcessingException {
        userClient = new UserClient();
        orderClient = new OrderClient();
        user = UserGenerator.getRandomEmailPasswordName();
        var createdUser =  userClient.create(user);
        accessToken =  createdUser.extract().path("accessToken");

    }

    @After
    public void tearDown(){
        userClient.delete(accessToken);
    }

    @DisplayName("Check /api/orders - success get orders with auth")
    @Test
    public void successGetOrdersWithAuthTest() {
        ValidatableResponse response = OrderClient.getOrdersWithAuth(accessToken);
        var statusCode = response.extract().statusCode();
        assertThat("Cannot get orders with auth", statusCode, equalTo(200));
    }

    @DisplayName("Check /api/orders - unSuccess get orders without auth")
    @Test
    public void unSuccessGetOrdersWithoutAuthTest() {
        ValidatableResponse response = OrderClient.getOrdersWithoutAuth();
        var statusCode = response.extract().statusCode();
        assertEquals("Can get orders without auth", 401, statusCode);
    }
}
