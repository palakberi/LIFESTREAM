package com.lifestream.repository;

import com.lifestream.model.Recipient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecipientRepository extends MongoRepository<Recipient, String> {
    Optional<Recipient> findByUsername(String username);
}
