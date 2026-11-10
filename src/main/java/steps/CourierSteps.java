package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CourierModel;

import static data.CourierData.COURIER_CREATE_PATH;
import static data.CourierData.COURIER_LOGIN_PATH;
import static io.restassured.RestAssured.given;

public class CourierSteps {
@Step("Create courier")
    public static Response createCourier(CourierModel courier) {
    return given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(courier)
            .when()
            .post(COURIER_CREATE_PATH)
            .then()
            .extract()
            .response();
    }

@Step("Successful courier login")
    public static Response loginCourier(CourierModel courier) {
    return  given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(courier)
            .when()
            .post(COURIER_LOGIN_PATH)
            .then()
            .extract()
            .response();
}
}
