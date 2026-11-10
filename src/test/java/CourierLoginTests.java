import io.qameta.allure.Description;
import model.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;

import static data.CourierData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.loginCourier;

public class CourierLoginTests extends BaseApiTest {
    private CourierModel courier;
    private String login;
    private String password;

    @Before
    public void creationOfCourier() {
        login = LOGIN + System.currentTimeMillis();
        password = PASSWORD;
        courier = new CourierModel(login, password, FIRST_NAME);

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    @Description("Successful courier login")
    public void successfulCourierLoginTest() {

        loginCourier(courier)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @Description("An attempt to log in courier without required field Login")
    public void courierLogInWithoutLoginTest() {

        CourierModel courierWithoutLogin = new CourierModel(null, password);

        loginCourier(courierWithoutLogin)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @Description("An attempt to log in courier without required field Password")
    public void courierLogInWithoutPasswordTest() {

        CourierModel courierWithoutPassword = new CourierModel(login, null);
        loginCourier(courierWithoutPassword)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

   @Test
   @Description("An attempt to log in courier with wrong login")
   public void courierLogInWithWrongLoginTest() {

       CourierModel courierWithWrongLogin = new CourierModel("wrongLogin", password);
       CourierSteps.loginCourier(courierWithWrongLogin)
               .then()
               .log().all()
               .statusCode(404)
               .body("message", equalTo("Учетная запись не найдена"));
   }

   @Test
   @Description("An attempt to log in courier with wrong password")
   public void courierLogInWithWrongPasswordTest() {

       CourierModel courierWithWrongPassword = new CourierModel(login, "wrongPassword");

        CourierSteps.loginCourier(courierWithWrongPassword)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
   }

   @Test
   @Description("An attempt to log in courier with unexisting login")
   public void courierLoginWithNonExistingLoginTest() {
       CourierModel courierWithNonExistingLogin = new CourierModel("nonExisting", password);
       CourierSteps.loginCourier(courierWithNonExistingLogin)
               .then()
               .log().all()
               .statusCode(404)
               .body("message", equalTo("Учетная запись не найдена"));

   }

    @After
    public void cleanUp() {
        CourierSteps.deleteCreatedCourier(courier);
    }
}
