package com.grupo.bd2.repository;

import com.grupo.bd2.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<Document,Long> {
}
