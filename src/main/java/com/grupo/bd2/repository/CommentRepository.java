package com.grupo.bd2.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.grupo.bd2.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByTaskId(String taskId);
    List<Comment> findByTaskIdAndEmployeeId(String taskId, String employeeId);
}
