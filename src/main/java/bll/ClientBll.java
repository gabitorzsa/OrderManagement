package bll;

import dao.ClientDAO;
import model.Client;

import java.util.List;

public class ClientBll {
    private final ClientDAO clientDAO = new ClientDAO();

    public void insert(int id, String name, String address) {
        Client client = new Client(id, name, address);
        clientDAO.insert(client);
    }

    public void update(int id, String name, String address) {
        Client client = new Client(id, name, address);
        clientDAO.update(client);
    }

    public void delete(int id) {
        Client client = new Client(id, null, null);
        clientDAO.delete(client);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }
}
