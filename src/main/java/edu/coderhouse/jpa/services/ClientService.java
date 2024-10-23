package edu.coderhouse.jpa.services;

import edu.coderhouse.jpa.entities.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {
    Client saveClient(Client client);
    Client getClientById(Integer id);
    List<Client> getAllClients();
    Client updateClient(Integer id, Client client);
    Client partialUpdateClient(Integer id, Map<String, Object> updates);
    void deleteClient(Integer id);
}
