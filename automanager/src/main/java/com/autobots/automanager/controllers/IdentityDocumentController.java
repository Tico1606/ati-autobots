package com.autobots.automanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entities.IdentityDocument;
import com.autobots.automanager.model.IdentityDocumentUpdater;
import com.autobots.automanager.repositories.IdentityDocumentRepository;

@RestController
@RequestMapping("/identity-documents")
public class IdentityDocumentController {

  @Autowired
  private IdentityDocumentRepository repository;

  @Autowired
  private IdentityDocumentUpdater updater;

  @GetMapping
  public ResponseEntity<List<IdentityDocument>> getAllDocuments() {
    List<IdentityDocument> documents = repository.findAll();
    return ResponseEntity.ok(documents);
  }

  @GetMapping("/{id}")
  public ResponseEntity<IdentityDocument> getDocumentById(@PathVariable Long id) {
    Optional<IdentityDocument> document = repository.findById(id);
    return document.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<IdentityDocument> createDocument(@RequestBody IdentityDocument document) {
    IdentityDocument saved = repository.save(document);
    return ResponseEntity.status(201).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<IdentityDocument> updateDocument(
      @PathVariable Long id,
      @RequestBody IdentityDocument updated) {

    Optional<IdentityDocument> existingOpt = repository.findById(id);
    if (existingOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    IdentityDocument existing = existingOpt.get();
    updater.update(existing, updated);
    repository.save(existing);

    return ResponseEntity.ok(existing);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
    Optional<IdentityDocument> documentOpt = repository.findById(id);
    if (documentOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    repository.delete(documentOpt.get());
    return ResponseEntity.noContent().build();
  }
}
