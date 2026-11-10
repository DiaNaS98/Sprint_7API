package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CourierModel;


import static data.CourierData.*;
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

@Step("Delete user")
    public static void deleteCreatedCourier(CourierModel courier) {
    Response response = loginCourier(courier);
    if (response.statusCode() == 200) {
        int id = response.path("id");
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .delete(COURIER_DELETE_PATH + id)
                .then()
                .statusCode(200);
    }
}
}
