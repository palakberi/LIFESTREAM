package com.lifestream.service;

import com.lifestream.model.Inventory;
import com.lifestream.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipientService {

    private final InventoryRepository inventoryRepository;

    public RecipientService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public String requestBlood(String bloodType, int units) {
        Optional<Inventory> invOpt = inventoryRepository.findByBloodType(bloodType);
        if (invOpt.isPresent()) {
            Inventory inv = invOpt.get();
            if (inv.getUnits() >= units) {
                inv.setUnits(inv.getUnits() - units);
                inventoryRepository.save(inv);
                return "Blood request fulfilled!";
            } else {
                return "Not enough units available";
            }
        } else {
            return "Blood type not found";
        }
    }
}
