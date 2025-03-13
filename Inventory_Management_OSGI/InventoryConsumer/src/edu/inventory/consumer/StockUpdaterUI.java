package edu.inventory.consumer;

import edu.inventory.producer.InventoryService;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class StockUpdaterUI extends JFrame {
    private InventoryService inventoryService;

    // Input fields for adding product
    private JTextField addProductField;
    private JTextField addQuantityField;

    // Output fields for getting product
    private JTextField getProductField;
    private JTextField getQuantityField;

    // Buttons
    private JButton addStockButton;
    private JButton getStockButton;
    private JButton getAllStockButton;
    private JButton removeProductButton;
    private JButton updateProductButton;
    private JButton clearInventoryButton;

    public StockUpdaterUI(InventoryService service) {
        this.inventoryService = service;

        // Frame settings
        setTitle("Stock Management");
        setSize(800, 500); // Adjusted size for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Use GridBagLayout for better control

        // Create constraints for GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Stock Section
        JPanel addStockPanel = createAddStockPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(addStockPanel, gbc);

        // Get Stock Section
        JPanel getStockPanel = createGetStockPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(getStockPanel, gbc);

        // Remove Product Section
        JPanel removeProductPanel = createRemoveProductPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(removeProductPanel, gbc);

        // Update Product Section
        JPanel updateProductPanel = createUpdateProductPanel();
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(updateProductPanel, gbc);

        // Clear Inventory Button
        clearInventoryButton = new JButton("Clear Inventory");
        clearInventoryButton.setToolTipText("Remove all products from inventory");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(clearInventoryButton, gbc);

        // Get All Stock Button
        getAllStockButton = new JButton("Get All Stock");
        getAllStockButton.setToolTipText("Display all products and their quantities");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(getAllStockButton, gbc);

        // Add Action Listeners
        addActionListeners();

        // Set default focus to the first input field
        addProductField.requestFocusInWindow();

        // Display the frame
        setVisible(true);
    }

    private JPanel createAddStockPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Add Stock"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel addProductLabel = new JLabel("Product Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(addProductLabel, gbc);

        addProductField = new JTextField(20);
        addProductField.setToolTipText("Enter the product name");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(addProductField, gbc);

        JLabel addQuantityLabel = new JLabel("Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(addQuantityLabel, gbc);

        addQuantityField = new JTextField(5);
        addQuantityField.setToolTipText("Enter the quantity");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(addQuantityField, gbc);

        addStockButton = new JButton("Add Stock");
        addStockButton.setToolTipText("Add the product to inventory");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(addStockButton, gbc);

        return panel;
    }

    private JPanel createGetStockPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Get Stock"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel getProductLabel = new JLabel("Product Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(getProductLabel, gbc);

        getProductField = new JTextField(20);
        getProductField.setToolTipText("Enter the product name");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(getProductField, gbc);

        JLabel getQuantityLabel = new JLabel("Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(getQuantityLabel, gbc);

        getQuantityField = new JTextField(5);
        getQuantityField.setEditable(false);
        getQuantityField.setToolTipText("Displayed quantity");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(getQuantityField, gbc);

        getStockButton = new JButton("Get Stock");
        getStockButton.setToolTipText("Retrieve the product quantity");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(getStockButton, gbc);

        return panel;
    }

    private JPanel createRemoveProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Remove Product"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel removeProductLabel = new JLabel("Product Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(removeProductLabel, gbc);

        JTextField removeProductField = new JTextField(20);
        removeProductField.setToolTipText("Enter the product name to remove");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(removeProductField, gbc);

        removeProductButton = new JButton("Remove Product");
        removeProductButton.setToolTipText("Remove the product from inventory");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(removeProductButton, gbc);

        return panel;
    }

    private JPanel createUpdateProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Update Product"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel updateProductLabel = new JLabel("Product Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(updateProductLabel, gbc);

        JTextField updateProductField = new JTextField(20);
        updateProductField.setToolTipText("Enter the product name to update");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(updateProductField, gbc);

        JLabel updateQuantityLabel = new JLabel("New Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(updateQuantityLabel, gbc);

        JTextField updateQuantityField = new JTextField(5);
        updateQuantityField.setToolTipText("Enter the new quantity");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(updateQuantityField, gbc);

        updateProductButton = new JButton("Update Product");
        updateProductButton.setToolTipText("Update the product quantity");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(updateProductButton, gbc);

        return panel;
    }

    private void addActionListeners() {
        // Button ActionListener for Adding Stock
        addStockButton.addActionListener(e -> {
            String product = addProductField.getText();
            try {
                int quantity = Integer.parseInt(addQuantityField.getText());
                if (quantity > 0) {
                    inventoryService.addStock(product, quantity);
                    JOptionPane.showMessageDialog(null, "Stock Updated for " + product + "!");
                    addProductField.setText("");
                    addQuantityField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Quantity must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button ActionListener for Getting Stock
        getStockButton.addActionListener(e -> {
            String product = getProductField.getText();
            int stock = inventoryService.getStock(product);
            getQuantityField.setText(String.valueOf(stock));
        });

        // Button ActionListener for Getting All Stock
        getAllStockButton.addActionListener(e -> {
            Map<String, Integer> allStock = inventoryService.getAll();
            StringBuilder stockList = new StringBuilder();
            for (Map.Entry<String, Integer> entry : allStock.entrySet()) {
                stockList.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
            }
            JOptionPane.showMessageDialog(null, stockList.toString(), "All Stock", JOptionPane.INFORMATION_MESSAGE);
        });

        // Button ActionListener for Removing Product
        removeProductButton.addActionListener(e -> {
            String product = ((JTextField) ((JPanel) removeProductButton.getParent()).getComponent(1)).getText();
            inventoryService.removeProduct(product);
            JOptionPane.showMessageDialog(null, "Product removed: " + product);
            ((JTextField) ((JPanel) removeProductButton.getParent()).getComponent(1)).setText("");
        });

        // Button ActionListener for Updating Product
        updateProductButton.addActionListener(e -> {
            String product = ((JTextField) ((JPanel) updateProductButton.getParent()).getComponent(1)).getText();
            try {
                int quantity = Integer.parseInt(((JTextField) ((JPanel) updateProductButton.getParent()).getComponent(3)).getText());
                if (quantity > 0) {
                    inventoryService.updateProduct(product, quantity);
                    JOptionPane.showMessageDialog(null, "Product updated: " + product + " = " + quantity);
                    ((JTextField) ((JPanel) updateProductButton.getParent()).getComponent(1)).setText("");
                    ((JTextField) ((JPanel) updateProductButton.getParent()).getComponent(3)).setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Quantity must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button ActionListener for Clearing Inventory
        clearInventoryButton.addActionListener(e -> {
            inventoryService.clearInventory();
            JOptionPane.showMessageDialog(null, "Inventory cleared.");
        });
    }

    public static void main(String[] args) {
        // Example usage
        InventoryService service = new InventoryService() {
            // Mock implementation for testing
            @Override
            public void addStock(String product, int quantity) {}
            @Override
            public int getStock(String product) { return 0; }
            @Override
            public Map<String, Integer> getAll() { return null; }
            @Override
            public void removeProduct(String product) {}
            @Override
            public void updateProduct(String product, int quantity) {}
            @Override
            public void clearInventory() {}
        };
        new StockUpdaterUI(service);
    }
}