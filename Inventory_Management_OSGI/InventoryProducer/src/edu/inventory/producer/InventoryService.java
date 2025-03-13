package edu.inventory.producer;

import java.util.Map;

public interface InventoryService {
    void addStock(String product, int quantity);
    int getStock(String product);
    Map<String, Integer> getAll();
    void removeProduct(String product); // New method to remove a product
    void updateProduct(String product, int quantity); // New method to update product quantity
    void clearInventory(); // New method to clear all products
}