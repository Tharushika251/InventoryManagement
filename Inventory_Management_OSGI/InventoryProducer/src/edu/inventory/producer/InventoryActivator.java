package edu.inventory.producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class InventoryActivator implements BundleActivator {
    private ServiceRegistration<InventoryService> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        InventoryService service = new InventoryServiceImpl();
        registration = context.registerService(InventoryService.class, service, null);
        System.out.println("Inventory Service Started...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
        System.out.println("Inventory Service Stopped...");
    }
}