package edu.inventory.consumer;

import edu.inventory.producer.InventoryService;

import javax.swing.SwingUtilities;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
    private ServiceReference<InventoryService> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Stock Updater Consumer Started...");

        // Get the reference to the InventoryService published by the producer
        serviceReference = context.getServiceReference(InventoryService.class);

        if (serviceReference != null) {
            InventoryService inventoryService = context.getService(serviceReference);

            // Launch the updated StockUpdaterUI
            SwingUtilities.invokeLater(() -> new StockUpdaterUI(inventoryService));
        } else {
            System.out.println("InventoryService not found!");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (serviceReference != null) {
            context.ungetService(serviceReference);
        }
        System.out.println("Stock Updater Consumer Stopped...");
    }
}