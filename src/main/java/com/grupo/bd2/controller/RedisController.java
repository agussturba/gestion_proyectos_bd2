package com.grupo.bd2.controller;

import com.grupo.bd2.model.RedisKeyValue;
import com.grupo.bd2.service.redis.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/redis")
public class RedisController {
    private final RedisService redisService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRedisKeyValueById(@PathVariable Long id){
       return ResponseEntity.ok().body(redisService.getRedisKeyValueById(id));
    }
    @PostMapping
    public ResponseEntity<?> createRedisKeyValue(@RequestBody RedisKeyValue redisKeyValue){
        return ResponseEntity.ok().body(redisService.save(redisKeyValue));
    }

}
