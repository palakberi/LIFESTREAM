package com.lifestream.config;

import com.lifestream.model.Inventory;
import com.lifestream.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    public DataLoader(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void run(String... args) {
        if (inventoryRepository.count() == 0) {
            inventoryRepository.save(new Inventory("A+", 5));
            inventoryRepository.save(new Inventory("B+", 3));
            inventoryRepository.save(new Inventory("O+", 7));
            inventoryRepository.save(new Inventory("AB-", 2));
            inventoryRepository.save(new Inventory("A-", 4));
            inventoryRepository.save(new Inventory("B-", 1));
        }
    }
}
