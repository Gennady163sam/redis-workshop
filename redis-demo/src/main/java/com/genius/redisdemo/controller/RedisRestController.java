package com.genius.redisdemo.controller;

import com.genius.redisdemo.domain.RedisKeyValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/storage")
public class RedisRestController {
    private RedisController redisController;

    @PostMapping
    public ResponseEntity<String> stringValue(@RequestBody RedisKeyValue persistanceEntity) {
        redisController.addValueStringByKey(persistanceEntity.getKey(), persistanceEntity.getValue());
        return ResponseEntity.ok(redisController.value(persistanceEntity.getKey()));
    }

}
