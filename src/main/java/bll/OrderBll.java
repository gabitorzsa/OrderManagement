package bll;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dao.OrdersDAO;
import dao.ProductDAO;
import model.Client;
import model.Orders;
import model.Product;

public class OrderBll {
    private OrdersDAO ordersDAO;

    private static int currentOrderIndex = 1;
    public OrderBll() {
        ordersDAO = new OrdersDAO();
    }

    public void createOrder(Client client, Product product, int quantity) {
        List<Orders> list = ordersDAO.findAll();
        int max = 0;
        for(Orders o: list)
            if(o.getId() > max) {
                max = o.getId();
            }
        Orders orders = new Orders(max + 1, client.getNameClient(), product.getNameProduct(), quantity);
        ordersDAO.insert(orders);

        product.setQuantity(product.getQuantity() - quantity);
        new ProductDAO().update(product);
        generateBill(orders, product);
    }

    private static void generateBill(Orders order, Product product) {
        try {
            double total = order.getQuantity();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Order" + (currentOrderIndex++) + ".pdf"));

            total= total * (product.getPrice());
            document.open();
            Chunk chunk1 = new Chunk("Client: "+ order.getNameClient()+ " bought "+ order.getQuantity()+ " " + order.getNameProduct());
            Paragraph p1 = new Paragraph(chunk1);

            Chunk chunk2 = new Chunk("at a cost of "+ product.getPrice() + " $/each item. Total: " + total);
            Paragraph p2 = new Paragraph(chunk2);

            document.add(p1);
            document.add(p2);
            document.close();
        }
        catch (FileNotFoundException | DocumentException e){
            e.printStackTrace();
        }
    }
}
