package presentation;

import bll.ClientBll;
import bll.ProductBll;
import model.Client;
import model.Product;

import javax.swing.*;
import java.lang.reflect.Field;

public class Controller {

    public Controller() {
        ClientView.clientView(this);
        ProductView.productView(this);
        OrderView.orderView(this);
    }

    public Object[] getTableHeader(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String[] header = new String[fields.length];
        for (int i = 0; i < fields.length; i++)
            header[i] = fields[i].getName();
        return header;
    }

    public Object[][] getTable(Object[] data) {
        if(data.length == 0)
            return null;

        Field[] fields = data[0].getClass().getDeclaredFields();
        Object[][] table = new Object[data.length][fields.length];
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    table[i][j] = fields[j].get(data[i]);
                }
            }
            return table;
        } catch (IllegalAccessException e) {
            System.out.println("AbstractBLL exception: " + e.getMessage());
        }
        return null;
    }

    public JTable createClientsTable() {
        return new JTable(getTable(new ClientBll().findAll().toArray()), getTableHeader(new Client()));
    }

    public JTable createProductsTable() {
        return new JTable(getTable(new ProductBll().findAll().toArray()), getTableHeader(new Product()));
    }

    public void updateProductTable() {
        ProductView.setTable();
    }

    public void updateProducts() {
        OrderView.setProducts(new ProductBll().findAll());
    }

    public void updateClients() {
        OrderView.setClients(new ClientBll().findAll());
    }
}
