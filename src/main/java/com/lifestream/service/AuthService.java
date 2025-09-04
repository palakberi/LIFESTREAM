package com.lifestream.service;

import com.lifestream.dto.LoginRequest;
import com.lifestream.dto.SignupRequest;
import com.lifestream.model.Donor;
import com.lifestream.model.Recipient;
import com.lifestream.repository.DonorRepository;
import com.lifestream.repository.RecipientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final DonorRepository donorRepository;
    private final RecipientRepository recipientRepository;
    private final PasswordEncoder passwordEncoder;
    private final InventoryService inventoryService;

    public AuthService(DonorRepository donorRepository,
                       RecipientRepository recipientRepository,
                       PasswordEncoder passwordEncoder,
                       InventoryService inventoryService) {
        this.donorRepository = donorRepository;
        this.recipientRepository = recipientRepository;
        this.passwordEncoder = passwordEncoder;
        this.inventoryService = inventoryService;
    }


    // ✅ Register new user
    public String signup(SignupRequest request) {
        if ("DONOR".equalsIgnoreCase(request.getRole())) {
            Donor donor = new Donor();
            donor.setUsername(request.getUsername());
            donor.setPassword(passwordEncoder.encode(request.getPassword()));
            donor.setBloodType(request.getBloodType());
            donorRepository.save(donor);

            // ❌ No inventory change here

            return "Donor registered successfully";
        } else if ("RECIPIENT".equalsIgnoreCase(request.getRole())) {
            Recipient rec = new Recipient();
            rec.setUsername(request.getUsername());
            rec.setPassword(passwordEncoder.encode(request.getPassword()));
            rec.setBloodType(request.getBloodType());
            recipientRepository.save(rec);

            return "Recipient registered successfully";
        }
        return "Invalid role";
    }



    // ✅ Login check
    public boolean login(LoginRequest request) {
        // ✅ Hardcoded admin login (for testing/demo)
        if ("admin".equalsIgnoreCase(request.getUsername())
                && "admin123".equals(request.getPassword())) {
            return true;
        }

        // Check donor
        Optional<Donor> donorOpt = donorRepository.findByUsername(request.getUsername());
        if (donorOpt.isPresent()) {
            Donor donor = donorOpt.get();
            return passwordEncoder.matches(request.getPassword(), donor.getPassword());
        }

        // Check recipient
        Optional<Recipient> recOpt = recipientRepository.findByUsername(request.getUsername());
        if (recOpt.isPresent()) {
            Recipient rec = recOpt.get();
            return passwordEncoder.matches(request.getPassword(), rec.getPassword());
        }

        return false;
    }



    public String getUserRole(String username) {
        if ("admin".equalsIgnoreCase(username)) {
            return "ADMIN";
        } else if (donorRepository.findByUsername(username).isPresent()) {
            return "DONOR";
        } else if (recipientRepository.findByUsername(username).isPresent()) {
            return "RECIPIENT";
        }
        return "UNKNOWN";
    }


}

