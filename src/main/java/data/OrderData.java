package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;

public class OrderData {
    static Faker order = new Faker();
    public static final String FIRST_NAME = "Иван";
    public static final String LAST_NAME = "Пупкин";
    public static final String ADDRESS = "ул. Иванова, д. 3";
    public static final String METRO_STATION = "1";
    public static final String PHONE = "+79998887766";
    public static final int RENT_TIME = 7;
    public static final String COMMENT = "Позвонить за 30 минут до доставки";
    public static final String ORDER_CREATION_PATH = "/api/v1/orders";
    public static final String ORDER_CANCELLATION_PATH = "/api/v1/orders/cancel";

    public static String deliveryDate() {
        return LocalDate.now().plusDays(6).toString();
    }
}
