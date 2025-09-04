package com.lifestream.controller;

import com.lifestream.model.Inventory;
import com.lifestream.repository.RecipientRepository;
import com.lifestream.service.InventoryService;
import com.lifestream.service.RecipientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/recipient")
public class RecipientController {

    private final InventoryService inventoryService;
    private final RecipientRepository recipientRepository;

    public RecipientController(InventoryService inventoryService,
                               RecipientRepository recipientRepository) {
        this.inventoryService = inventoryService;
        this.recipientRepository = recipientRepository;
    }

    @PostMapping("/request")
    public ResponseEntity<String> requestBlood(@RequestParam String recipientId,
                                               @RequestParam String bloodType) {
        // Check stock
        Inventory inv = inventoryService.adjustStock(bloodType, -1);
        if (inv.getUnits() < 0) {
            inv.setUnits(0);
            inventoryService.adjustStock(bloodType, 0); // reset
            return ResponseEntity.badRequest().body("❌ Not enough stock available");
        }
        return ResponseEntity.ok("✅ Blood issued successfully to recipient " + recipientId);
    }
}
