package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.IdentityDocument;

public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, Long> {
}
