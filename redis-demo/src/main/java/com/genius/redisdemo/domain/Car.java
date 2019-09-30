package com.genius.redisdemo.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Car {
    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;

    public Map<String, Object> asMap() {
        return Map.of("id", String.valueOf(id), "name", name, "longitude", String.valueOf(longitude), "latitude", String.valueOf(latitude));
    }

    public static Car fromMap(Map<String, String> map) {
        Car car = new Car();
        car.setId(Long.parseLong(map.get("id")));
        car.setName(map.get("name"));
        car.setLatitude(Double.parseDouble(map.get("latitude")));
        car.setLongitude(Double.parseDouble(map.get("longitude")));
        return car;
    }
}
