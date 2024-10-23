package edu.coderhouse.jpa.services;

import edu.coderhouse.jpa.entities.Invoice;

import java.util.List;
import java.util.Map;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    Invoice getInvoiceById(Integer id);
    List<Invoice> getAllInvoice();
    Invoice updateInvoice(Integer id, Invoice invoice);
    Invoice partialUpdateInvoice(Integer id, Map<String, Object> updates);
    void deleteInvoice(Integer id);
}
