package com.lifestream.service;

import com.lifestream.model.Inventory;
import com.lifestream.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    public Inventory adjustStock(String bloodType, int delta) {
        Inventory inv = inventoryRepository.findByBloodType(bloodType)
                .orElseGet(() -> {
                    Inventory i = new Inventory();
                    i.setBloodType(bloodType);
                    i.setUnits(0);
                    return i;
                });

        inv.setUnits(inv.getUnits() + delta);
        return inventoryRepository.save(inv);
    }

}
