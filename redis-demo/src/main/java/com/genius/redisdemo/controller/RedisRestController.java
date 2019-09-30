package com.genius.redisdemo.controller;

import com.genius.redisdemo.domain.Car;
import com.genius.redisdemo.domain.ListObject;
import com.genius.redisdemo.domain.SimpleObject;
import com.genius.redisdemo.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/storage")
public class RedisRestController {
    private RedisService redisService;

    public RedisRestController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping
    public ResponseEntity<String> stringValue(@RequestBody SimpleObject persistEntity) {
        redisService.addValueStringByKey(persistEntity.getKey(), persistEntity.getValue());
        return ResponseEntity.ok(redisService.value(persistEntity.getKey()));
    }

    @PostMapping("/list")
    public ResponseEntity<String> listValue(@RequestBody ListObject persistEntity) {
        redisService.addArrayByKey(persistEntity.getKey(), persistEntity.getValue());
        return ResponseEntity.ok(redisService.list(persistEntity.getKey()));
    }

    @PostMapping("/list/add")
    public ResponseEntity<Set<String>> addValueToList(@RequestBody SimpleObject persistEntity) {
        redisService.addElementToArray(persistEntity.getKey(), persistEntity.getValue());
        return ResponseEntity.ok(redisService.set(persistEntity.getKey()));
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        car.asMap()
                .forEach((key, value) -> redisService.changeObjectAttribute("car#" + car.getId(), key, String.valueOf(value)));
        return ResponseEntity.ok(Car.fromMap(redisService.object("car#"+car.getId())));
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<Car> addCar(@PathVariable Long carId) {
        return ResponseEntity.ok(Car.fromMap(redisService.object("car#"+carId)));
    }

}
