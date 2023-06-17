package com.example.filedemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.filedemo.entity.Item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private List<Item> inventoryItems = new ArrayList<>();

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(inventoryItems);
    }

    @PostMapping("/items")
    public ResponseEntity<Void> createItem(@RequestBody Item newItem) {
        inventoryItems.add(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/items/{id}/status")
    public ResponseEntity<Void> updateItemStatus(@PathVariable("id") int itemId, @RequestBody StatusUpdateRequest request) {
        for (Item item : inventoryItems) {
            if (item.getId() == itemId) {
                item.setStatus(request.getStatus());
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable("id") int itemId) {
        Item itemToRemove = null;
        for (Item item : inventoryItems) {
            if (item.getId() == itemId) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            inventoryItems.remove(itemToRemove);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Helper class for status update request
    private static class StatusUpdateRequest {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

