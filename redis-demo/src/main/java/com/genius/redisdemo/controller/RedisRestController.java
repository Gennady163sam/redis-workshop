package com.genius.redisdemo.controller;

import com.genius.redisdemo.domain.ListObject;
import com.genius.redisdemo.domain.SimpleObject;
import com.genius.redisdemo.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
