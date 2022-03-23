package model;

public class Client {
    private int id;
    private String nameClient;
    private String addressClient;

    public Client() {}

    public Client(int id, String name, String address) {
        super();
        this.id = id;
        this.nameClient = name;
        this.addressClient = address;
    }

    @Override
    public String toString() {
        return "Client [ID client = " + id + ", name = " + nameClient + ", address = " + addressClient + "]";
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

    public String getAddressClient() {
        return addressClient;
    }

    public void setAddressClient(String addressClient) {
        this.addressClient = addressClient;
    }
}
