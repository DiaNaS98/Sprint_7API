package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderModel;

import static data.OrderData.ORDER_CANCELLATION_PATH;
import static data.OrderData.ORDER_CREATION_PATH;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Order creation")
    public static Response orderCreation(OrderModel order) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order.toMap())
                .when()
                .post(ORDER_CREATION_PATH)
                .then()
                .extract()
                .response();
    }

    @Step("Get list of orders")
    public static Response gettingListOfOrders() {
        return given()
                .log().all()
                .when()
                .get(ORDER_CREATION_PATH)
                .then()
                .extract()
                .response();
    }

    @Step("Cancelling an order")
    public static Response cancellingOrder(int track) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(String.format("{ \"track\": %d }", track))
                .when()
                .put(ORDER_CANCELLATION_PATH)
                .then()
                .extract()
                .response();
    }
}
