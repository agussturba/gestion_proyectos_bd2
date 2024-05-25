package com.grupo.bd2.service.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.DocumentDto;
import com.grupo.bd2.dto.RequestDocumentDto;
import com.grupo.bd2.model.Document;
import com.grupo.bd2.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MongoServiceImpl implements MongoService {
    private final DocumentRepository documentRepository;
    private static final  ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public DocumentDto save(RequestDocumentDto document) {
        final var savedDocument = documentRepository.save(objectMapper.convertValue(document, Document.class));
        return objectMapper.convertValue(savedDocument, DocumentDto.class);
    }

}
