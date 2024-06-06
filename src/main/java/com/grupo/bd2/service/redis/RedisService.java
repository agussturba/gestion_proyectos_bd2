package com.grupo.bd2.service.redis;

import com.grupo.bd2.dto.RedisDto;
import com.grupo.bd2.model.RedisKeyValue;


public interface RedisService {

    RedisKeyValue getRedisKeyValueById(Long id);

    RedisDto save(RedisKeyValue redisKeyValue);

}
