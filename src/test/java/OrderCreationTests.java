import data.OrderData;
import io.qameta.allure.Description;
import model.OrderModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(Parameterized.class)
public class OrderCreationTests extends BaseApiTest{
    private final List<String> color;
    private int track = 0;

    public OrderCreationTests(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[] [] colorOptions() {
        return new Object[][] {
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY", "BLACK")},
                {null}
        };
    }

@Test
@Description("The creation of an order with different color combinations and with no color at all")
    public void orderCreationWithDifferentColorOptionsTest() {
    OrderModel order = new OrderModel(OrderData.FIRST_NAME, OrderData.LAST_NAME, OrderData.ADDRESS, OrderData.METRO_STATION, OrderData.PHONE, OrderData.RENT_TIME, OrderData.deliveryDate(), OrderData.COMMENT, color);
    track = OrderSteps.orderCreation(order)
            .then()
            .log().all()
            .statusCode(201)
            .body("track", notNullValue())
            .body("track", greaterThan(0))
            .extract()
            .path("track");
}

@After
    public void cleanUp() {
        if (track > 0) {
            OrderSteps.cancellingOrder(track);
        }
}
}
