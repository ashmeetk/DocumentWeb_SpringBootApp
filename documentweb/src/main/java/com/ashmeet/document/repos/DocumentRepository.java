package com.ashmeet.document.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashmeet.document.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
