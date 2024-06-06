package com.grupo.bd2.service.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.RedisDto;
import com.grupo.bd2.model.RedisKeyValue;
import com.grupo.bd2.repository.RedisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RedisServiceImpl implements RedisService{
    private final RedisRepository redisRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public RedisKeyValue getRedisKeyValueById(Long id) {
        return redisRepository.findById(id).orElse(null);
    }

    @Override
    public RedisDto save(RedisKeyValue redisKeyValue) {
        return objectMapper.convertValue(redisRepository.save(redisKeyValue),RedisDto.class);
    }
}
