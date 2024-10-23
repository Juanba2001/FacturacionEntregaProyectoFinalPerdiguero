package edu.coderhouse.jpa.services.impl;

import edu.coderhouse.jpa.entities.Client;
import edu.coderhouse.jpa.repositories.ClientRepository;
import edu.coderhouse.jpa.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
    @Override
    public Client getClientById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    @Override
    public Client updateClient(Integer id, Client client) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            existingClient.setName(client.getName());
            existingClient.setLastname(client.getLastname());
            existingClient.setDocnumber(client.getDocnumber());
            return clientRepository.save(existingClient);
        }
        return null;
    }
    @Override
    public Client partialUpdateClient(Integer id, Map<String, Object> updates) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            updates.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Client.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingClient, value);
                }
            });
            return clientRepository.save(existingClient);
        }
        return null;
    }
    @Override
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
