package com.grupo.bd2.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.grupo.bd2.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByTaskId(Long taskId);
    List<Comment> findByTaskIdAndEmployeeId(Long taskId, Long employeeId);
}
