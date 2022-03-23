package bll;
import java.util.List;
import dao.ProductDAO;
import model.Product;

public class ProductBll {

    private ProductDAO productDAO;

    public ProductBll() {
        productDAO = new ProductDAO();
    }

    public void insert(int id, String nameProduct, int quantity, int price) {
        Product product = new Product(id, nameProduct, quantity, price);
        productDAO.insert(product);
    }

    public void update(int id, String nameProduct, int quantity, int price) {
        Product product = new Product(id, nameProduct, quantity, price);
        productDAO.update(product);
    }

    public void delete(int id) {
        Product product = new Product(id, null, 0, 0);
        productDAO.delete(product);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }
}
