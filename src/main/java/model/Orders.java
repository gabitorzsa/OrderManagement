package model;

public class Orders {
    private int id;
    private String nameClient;
    private String nameProduct;
    private int quantity;

    public Orders() {}

    public Orders(int id, String nameClient, String nameProduct, int quantity) {
        super();
        this.id = id;
        this.nameClient = nameClient;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order [ID Order = " + id + ", Client name = " + nameClient + ", Product name = " + nameProduct + ", Quantity = " + quantity + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
