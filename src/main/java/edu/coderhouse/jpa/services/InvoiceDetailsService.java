package edu.coderhouse.jpa.services;

import edu.coderhouse.jpa.entities.InvoiceDetails;

import java.util.List;
import java.util.Map;

public interface InvoiceDetailsService {
    InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetails);
    InvoiceDetails getInvoiceDetailsById(Integer id);
    List<InvoiceDetails> getAllInvoiceDetails();
    InvoiceDetails updateInvoiceDetails(Integer id, InvoiceDetails invoiceDetails);
    InvoiceDetails partialUpdateInvoiceDetails(Integer id, Map<String, Object> updates);
    void deleteInvoiceDetails(Integer id);
}
