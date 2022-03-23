package presentation;

import bll.OrderBll;
import model.Client;
import model.Product;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class OrderView {
    private static final JComboBox<Client> clientJComboBox = new JComboBox<>();
    private static final JComboBox<Product> productJComboBox = new JComboBox<>();

    public static void orderView(final Controller controller) {
        JFrame orderFrame = new JFrame("Orders");
        orderFrame.setVisible(true);
        orderFrame.setSize(400,400);

        JPanel orderPanel = new JPanel();

        // Field Panel
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(3, 2));
        JLabel orderClientIDLabel = new JLabel("Client ID: ");
        JLabel orderProductIDLabel = new JLabel("Product ID: ");
        JLabel orderQuantityLabel = new JLabel("Quantity: ");
        final JTextField orderQuantityField = new JTextField("");

        controller.updateClients();
        controller.updateProducts();

        fieldPanel.add(orderClientIDLabel);
        fieldPanel.add(clientJComboBox);
        fieldPanel.add(orderProductIDLabel);
        fieldPanel.add(productJComboBox);
        fieldPanel.add(orderQuantityLabel);
        fieldPanel.add(orderQuantityField);

        // button
        JPanel buttonPanel = new JPanel();
        JButton orderButton = new JButton("Order");
        buttonPanel.add(orderButton, BorderLayout.CENTER);

        // add all components
        orderPanel.add(fieldPanel);
        orderPanel.add(buttonPanel);

        orderFrame.setContentPane(orderPanel);
        orderFrame.pack();
        orderFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        orderButton.addActionListener( e -> {

            int quantity = Integer.parseInt(orderQuantityField.getText());
            Client client = (Client) clientJComboBox.getSelectedItem();
            Product product = (Product) productJComboBox.getSelectedItem();

            if(product.getQuantity() < quantity)
            {
                JOptionPane.showMessageDialog(null, "Invalid quantity");
            } else {
                try {
                    new OrderBll().createOrder(client, product, quantity);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                controller.updateProductTable();
            }
        });
    }

    public static void setClients(List<Client> clientList) {
        System.out.println(clientList);
        clientJComboBox.removeAllItems();
        for(Client client: clientList)
            clientJComboBox.addItem(client);
        clientJComboBox.revalidate();
        System.out.println(clientJComboBox);
    }

    public static void setProducts(List<Product> productList) {
        productJComboBox.removeAllItems();
        for(Product product: productList)
            productJComboBox.addItem(product);
        productJComboBox.revalidate();
    }
}
