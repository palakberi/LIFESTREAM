package com.lifestream.controller;

import com.lifestream.model.Inventory;
import com.lifestream.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @PostMapping("/adjust")
    public ResponseEntity<Inventory> adjust(@RequestParam String bloodType,
                                            @RequestParam int delta) {
        return ResponseEntity.ok(inventoryService.adjustStock(bloodType, delta));
    }
}
