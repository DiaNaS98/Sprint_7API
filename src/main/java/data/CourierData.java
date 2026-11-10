package data;
import com.github.javafaker.Faker;

public class CourierData {
    static Faker user = new Faker();
    public static final String LOGIN = user.name().lastName() + System.currentTimeMillis();
    public static final String PASSWORD = user.regexify("[0-9]{4}");
    public static final String FIRST_NAME = "dayeneris";
    public static final String COURIER_CREATE_PATH = "/api/v1/courier";
    public static final String COURIER_LOGIN_PATH = "/api/v1/courier/login";
}
