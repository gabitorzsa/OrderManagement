package presentation;

import bll.ClientBll;
import dao.AbstractDAO;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class ClientView extends JFrame {

    public static void clientView(final Controller controller) {
        JFrame clientFrame = new JFrame("Clients");
        clientFrame.setVisible(true);
        clientFrame.setSize(400,400);

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));

        /// Field Panel
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(3, 2));
        JLabel clientIDLabel = new JLabel("Client ID: ");
        final JTextField clientIDField = new JTextField("");
        JLabel clientNameLabel = new JLabel("Client name: ");
        final JTextField clientNameField = new JTextField("");
        JLabel clientAddressLabel = new JLabel("Client address: ");
        final JTextField clientAddressField = new JTextField("");
        fieldPanel.add(clientIDLabel);
        fieldPanel.add(clientIDField);
        fieldPanel.add(clientNameLabel);
        fieldPanel.add(clientNameField);
        fieldPanel.add(clientAddressLabel);
        fieldPanel.add(clientAddressField);

        // buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 2));
        JButton addButton = new JButton("Add client");
        JButton deleteButton = new JButton("Delete client");
        JButton editButton = new JButton("Edit client");
        JButton viewTableButton = new JButton("View table Client");
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(viewTableButton);

        // Show table area
        final JScrollPane tableArea = new JScrollPane();
        JTable startTable = controller.createClientsTable();
        tableArea.setViewportView(startTable);
        tableArea.setColumnHeaderView(startTable.getTableHeader());

        // add all components
        clientPanel.add(fieldPanel);
        clientPanel.add(buttonsPanel);
        clientPanel.add(tableArea);

        clientFrame.setContentPane(clientPanel);
        clientFrame.pack();
        clientFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addButton.addActionListener( e -> {
            int clientID = Integer.parseInt(clientIDField.getText());
            String clientName = clientNameField.getText();
            String clientAddress = clientAddressField.getText();
            try {
                new ClientBll().insert(clientID, clientName, clientAddress);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
            JTable table = controller.createClientsTable();
            tableArea.setViewportView(table);
            tableArea.setColumnHeaderView(table.getTableHeader());
            controller.updateClients();
        });

        editButton.addActionListener( e -> {
            int clientID = Integer.parseInt(clientIDField.getText());
            String clientName = clientNameField.getText();
            String clientAddress = clientAddressField.getText();
            try {
                new ClientBll().update(clientID, clientName, clientAddress);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
            JTable table = controller.createClientsTable();
            tableArea.setViewportView(table);
            tableArea.setColumnHeaderView(table.getTableHeader());
            controller.updateClients();
        });

        deleteButton.addActionListener( e -> {
            int clientID = Integer.parseInt(clientIDField.getText());
            try {
                new ClientBll().delete(clientID);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
            JTable table = controller.createClientsTable();
            tableArea.setViewportView(table);
            tableArea.setColumnHeaderView(table.getTableHeader());
            controller.updateClients();
        });
    }
}
