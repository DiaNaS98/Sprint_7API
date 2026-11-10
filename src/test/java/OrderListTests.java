import io.qameta.allure.Description;
import org.junit.Test;
import steps.OrderSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTests extends BaseApiTest{
    @Test
    @Description("The request returns with list of orders")
    public void gettingOrdersListTest() {
        OrderSteps.gettingListOfOrders()
                .then()
                .log().all()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
