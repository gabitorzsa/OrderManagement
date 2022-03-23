package presentation;

import bll.ProductBll;
import model.Product;

import javax.swing.*;
import java.awt.*;

public class ProductView {

    private static final JScrollPane tableArea = new JScrollPane();
    private static Controller controller;

    public static void productView(final Controller controller) {
        ProductView.controller = controller;
        JFrame productFrame = new JFrame("Products");
        productFrame.setVisible(true);
        productFrame.setSize(400,400);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        /// Field Panel
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(4, 2));
        JLabel productIDLabel = new JLabel("Product ID: ");
        final JTextField productIDField = new JTextField("");
        JLabel productNameLabel = new JLabel("Product name: ");
        final JTextField productNameField = new JTextField("");
        JLabel productPriceLabel = new JLabel("Product price: ");
        final JTextField productPriceField = new JTextField("");
        JLabel productQuantityLabel = new JLabel("Product quantity: ");
        final JTextField productQuantityField = new JTextField("");
        fieldPanel.add(productIDLabel);
        fieldPanel.add(productIDField);
        fieldPanel.add(productNameLabel);
        fieldPanel.add(productNameField);
        fieldPanel.add(productPriceLabel);
        fieldPanel.add(productPriceField);
        fieldPanel.add(productQuantityLabel);
        fieldPanel.add(productQuantityField);

        // buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 2));
        JButton addButton = new JButton("Add product");
        JButton deleteButton = new JButton("Delete product");
        JButton editButton = new JButton("Edit product");
        JButton viewTableButton = new JButton("View table product");
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(viewTableButton);

        // Show table area
        JTable startTable = controller.createProductsTable();
        tableArea.setViewportView(startTable);
        tableArea.setColumnHeaderView(startTable.getTableHeader());

        // add all components
        productPanel.add(fieldPanel);
        productPanel.add(buttonsPanel);
        productPanel.add(tableArea);

        productFrame.setContentPane(productPanel);
        productFrame.pack();
        productFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        addButton.addActionListener( e -> {
            int productID = Integer.parseInt(productIDField.getText());
            String productName = productNameField.getText();
            int productQuantity = Integer.parseInt(productQuantityField.getText());
            int productPrice = Integer.parseInt(productPriceField.getText());
            try {
                new ProductBll().insert(productID, productName, productQuantity, productPrice);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
            setTable();
        });

        editButton.addActionListener( e -> {
            int productID = Integer.parseInt(productIDField.getText());
            String productName = productNameField.getText();
            int productQuantity = Integer.parseInt(productQuantityField.getText());
            int productPrice = Integer.parseInt(productPriceField.getText());
            try {
                new ProductBll().update(productID, productName, productQuantity, productPrice);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
            setTable();
        });

        deleteButton.addActionListener( e -> {
            int productID = Integer.parseInt(productIDField.getText());
            try {
                new ProductBll().delete(productID);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
            setTable();
        });
    }

    public static void setTable() {
        JTable table = controller.createProductsTable();
        tableArea.setViewportView(table);
        tableArea.setColumnHeaderView(table.getTableHeader());
        controller.updateProducts();
        tableArea.revalidate();
    }
}
