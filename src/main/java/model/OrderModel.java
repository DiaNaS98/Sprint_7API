package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class OrderModel {
private String firstName;
private String lastName;
private String address;
private String metroStation;
private String phone;
private int rentTime;
private String deliveryDate;
private String comment;
private List<String> color;

public Map<String, Object> toMap() {
    Map<String, Object> map = new LinkedHashMap<>();
    if (firstName != null) {
        map.put("firstName", firstName);
    }
    if (lastName != null) {
        map.put("lastName", lastName);
    }
    if (address != null) {
        map.put("address", address);
    }
    if (metroStation != null) {
        map.put("metroStation", metroStation);
    }
    if (phone != null) {
        map.put("phone", phone);
    }
    if (deliveryDate != null) {
        map.put("deliveryDate", deliveryDate);
    }
    if (comment != null) {
        map.put("comment", comment);
    }
    if (color != null) {
        map.put("color", color);
    }
    return map;
}
}
