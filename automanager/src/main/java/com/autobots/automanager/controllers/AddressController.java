package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Address;
import com.autobots.automanager.model.AddressUpdater;
import com.autobots.automanager.repositories.AddressRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

  private final AddressRepository repository;
  private final AddressUpdater updater;

  public AddressController(AddressRepository repository, AddressUpdater updater) {
    this.repository = repository;
    this.updater = updater;
  }

  @PostMapping
  public ResponseEntity<Address> createAddress(@RequestBody Address address) {
    Address saved = repository.save(address);
    return ResponseEntity.status(201).body(saved);
  }

  @GetMapping
  public ResponseEntity<List<Address>> getAllAddresses() {
    List<Address> addresses = repository.findAll();
    return ResponseEntity.ok(addresses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
    Optional<Address> address = repository.findById(id);
    return address.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address updated) {
    Optional<Address> existingOpt = repository.findById(id);
    if (existingOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Address existing = existingOpt.get();
    updater.update(existing, updated);
    repository.save(existing);

    return ResponseEntity.ok(existing);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
    if (!repository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
