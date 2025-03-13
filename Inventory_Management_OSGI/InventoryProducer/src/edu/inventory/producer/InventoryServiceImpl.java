package edu.inventory.producer;

import java.util.HashMap;
import java.util.Map;

public class InventoryServiceImpl implements InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();

    // Constructor to initialize the HashMap with example data
    public InventoryServiceImpl() {
        // Add example dataset
        stock.put("Laptop", 10);
        stock.put("Mouse", 50);
        stock.put("Keyboard", 30);
        stock.put("Monitor", 15);
        stock.put("Printer", 5);

        System.out.println("Example dataset initialized:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    @Override
    public void addStock(String product, int quantity) {
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
        System.out.println("Stock updated: " + product + " = " + stock.get(product));
    }

    @Override
    public int getStock(String product) {
        return stock.getOrDefault(product, 0);
    }

    @Override
    public Map<String, Integer> getAll() {
        return new HashMap<>(stock); // Return a copy of the stock map
    }

    @Override
    public void removeProduct(String product) {
        if (stock.containsKey(product)) {
            stock.remove(product);
            System.out.println("Product removed: " + product);
        } else {
            System.out.println("Product not found: " + product);
        }
    }

    @Override
    public void updateProduct(String product, int quantity) {
        if (stock.containsKey(product)) {
            stock.put(product, quantity);
            System.out.println("Product updated: " + product + " = " + quantity);
        } else {
            System.out.println("Product not found: " + product);
        }
    }

    @Override
    public void clearInventory() {
        stock.clear();
        System.out.println("Inventory cleared.");
    }
}