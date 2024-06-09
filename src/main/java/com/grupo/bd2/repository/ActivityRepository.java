package com.grupo.bd2.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.grupo.bd2.model.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByProjectId(String projectId);
}