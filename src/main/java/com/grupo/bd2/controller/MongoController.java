package com.grupo.bd2.controller;

import com.grupo.bd2.dto.DocumentDto;
import com.grupo.bd2.dto.RequestDocumentDto;
import com.grupo.bd2.model.Document;
import com.grupo.bd2.service.mongo.MongoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/mongo")
public class MongoController {
   private final MongoService mongoService;

   @GetMapping
   public ResponseEntity<List<Document>> getAllDocuments(){
       return ResponseEntity.ok().body(mongoService.getDocuments());
   }
    @PostMapping
    public ResponseEntity<DocumentDto> saveDocument(@RequestBody RequestDocumentDto requestDocumentDto){
        mongoService.save(requestDocumentDto);
        return ResponseEntity.ok().body(DocumentDto.builder().name("agustin").build());
    }
}
