package edu.inventory.consumer;

import edu.inventory.producer.InventoryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class StockUpdater implements BundleActivator {
    private ServiceReference<InventoryService> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        // Get the InventoryService reference
        serviceReference = context.getServiceReference(InventoryService.class);

        if (serviceReference != null) {
            // Retrieve the InventoryService instance
            InventoryService inventoryService = context.getService(serviceReference);

            // Update stock for a specific item
            inventoryService.addStock("Laptop", 5);

            // Print the updated stock information
            System.out.println("Updated stock for Laptop: " + inventoryService.getStock("Laptop"));
        } else {
            // If InventoryService is not found, print an error
            System.out.println("InventoryService not found!");
        }
    }
    @Override
    public void stop(BundleContext context) throws Exception {
        // Unget the service reference and clean up
        context.ungetService(serviceReference);

        // Log when the Stock Updater Service is stopped
        System.out.println("Stock Updater Service Stopped...");
    }
}