package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.PhoneNumber;
import com.autobots.automanager.model.PhoneNumberUpdater;
import com.autobots.automanager.repositories.PhoneNumberRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phone-numbers")
public class PhoneNumberController {

  private final PhoneNumberRepository repository;
  private final PhoneNumberUpdater updater;

  public PhoneNumberController(PhoneNumberRepository repository, PhoneNumberUpdater updater) {
    this.repository = repository;
    this.updater = updater;
  }

  @GetMapping
  public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbers() {
    List<PhoneNumber> numbers = repository.findAll();
    return ResponseEntity.ok(numbers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PhoneNumber> getPhoneNumberById(@PathVariable Long id) {
    Optional<PhoneNumber> phone = repository.findById(id);
    return phone.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<PhoneNumber> createPhoneNumber(@RequestBody PhoneNumber phone) {
    PhoneNumber saved = repository.save(phone);
    return ResponseEntity.status(201).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PhoneNumber> updatePhoneNumber(@PathVariable Long id, @RequestBody PhoneNumber updated) {
    Optional<PhoneNumber> existingOpt = repository.findById(id);
    if (existingOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    PhoneNumber existing = existingOpt.get();
    updater.update(existing, updated);
    repository.save(existing);

    return ResponseEntity.ok(existing);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePhoneNumber(@PathVariable Long id) {
    if (!repository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
