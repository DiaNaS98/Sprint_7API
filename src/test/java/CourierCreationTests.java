import io.qameta.allure.Description;
import model.CourierModel;
import org.junit.After;
import org.junit.Test;

import static data.CourierData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.createCourier;

public class CourierCreationTests extends BaseApiTest {

    @Test
    @Description("Creation of a courier with valid data")
    public void successfulCourierCreationTest() {
        CourierModel courier = new CourierModel(LOGIN + System.currentTimeMillis(), PASSWORD, FIRST_NAME + System.currentTimeMillis());

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));

    }

    @Test
    @Description("Creation of duplicated courier")
    public void creationOfDuplicatedCouriersTest() {
        CourierModel courier = new CourierModel(LOGIN + System.currentTimeMillis(),PASSWORD,FIRST_NAME + System.currentTimeMillis());

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    @Test
    @Description("An attempt to create courier without required field Password")
    public void creationOfCourierWithoutPasswordTest() {
        CourierModel courier = new CourierModel(LOGIN + System.currentTimeMillis(), null, FIRST_NAME + System.currentTimeMillis());

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    @Description("An attempt to create courier without required field Login")
    public void creationOfCourierWithoutLoginTest() {
        CourierModel courier = new CourierModel(null, PASSWORD, FIRST_NAME);

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @Description("An attempt to create courier with Login that already exists")
    public void creationOfCourierWithExistingLogin() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRST_NAME);

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));

        CourierModel courierWithExistingLogin = new CourierModel(LOGIN, PASSWORD, FIRST_NAME);

        createCourier(courierWithExistingLogin)
                .then()
                .log().all()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void cleanUp() {
    }
}
