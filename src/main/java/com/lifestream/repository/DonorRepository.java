package com.lifestream.repository;

import com.lifestream.model.Donor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DonorRepository extends MongoRepository<Donor, String> {
    Optional<Donor> findByUsername(String username);
}
