package edu.coderhouse.jpa.services.impl;

import edu.coderhouse.jpa.entities.Invoice;
import edu.coderhouse.jpa.repositories.InvoiceRepository;
import edu.coderhouse.jpa.services.InvoiceService;
import edu.coderhouse.jpa.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private TimeService timeService;
    @Override
    public Invoice saveInvoice(Invoice invoice) {
        LocalDateTime currentDateTime = timeService.getCurrentDateTime();

        invoice.setCreated_at(currentDateTime);

        return invoiceRepository.save(invoice);
    }
    @Override
    public Invoice getInvoiceById(Integer id) {
        return invoiceRepository.findById(id).orElse(null);
    }
    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }
    @Override
    public Invoice updateInvoice(Integer id, Invoice invoice) {
        Invoice existingInvoice = invoiceRepository.findById(id).orElse(null);
        if (existingInvoice != null) {
            existingInvoice.setClient(invoice.getClient());
            existingInvoice.setCreated_at(invoice.getCreated_at());
            existingInvoice.setTotal(invoice.getTotal());
            return invoiceRepository.save(existingInvoice);
        }
        return null;
    }
    @Override
    public Invoice partialUpdateInvoice(Integer id, Map<String, Object> updates) {
        Invoice existingInvoice = invoiceRepository.findById(id).orElse(null);
        if (existingInvoice != null) {
            updates.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Invoice.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingInvoice, value);
                }
            });
            return invoiceRepository.save(existingInvoice);
        }
        return null;
    }
    @Override
    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }
}
