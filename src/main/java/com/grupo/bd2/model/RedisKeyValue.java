package com.grupo.bd2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Data
public class RedisKeyValue {
    @Id
    private Long key;
    private String value;
}
