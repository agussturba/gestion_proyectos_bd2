package com.grupo.bd2.service.mongo;

import com.grupo.bd2.dto.DocumentDto;
import com.grupo.bd2.dto.RequestDocumentDto;
import com.grupo.bd2.model.Document;

import java.util.List;

public interface MongoService {
    List<Document> getDocuments();

    DocumentDto save(RequestDocumentDto document);
}
