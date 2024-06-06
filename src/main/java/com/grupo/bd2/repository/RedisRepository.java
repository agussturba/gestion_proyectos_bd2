package com.grupo.bd2.repository;

import com.grupo.bd2.model.RedisKeyValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<RedisKeyValue,Long> {
}
