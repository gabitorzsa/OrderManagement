package model;

public class Product {
    private int id;
    private String nameProduct;
    private int price;
    private int quantity;

    public Product() {}

    public Product(int id, String nameProduct, int quantity, int price) {
        super();
        this.id = id;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.price = price;
    }
    @Override
    public String toString() {
        return "Product [ID Product = " + id + ", Product name = " + nameProduct + ", Price = " + price + ", Quantity = " + quantity + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
