package edu.coderhouse.jpa.services.impl;

import edu.coderhouse.jpa.entities.InvoiceDetails;
import edu.coderhouse.jpa.entities.Product;
import edu.coderhouse.jpa.repositories.InvoiceDetailsRepository;
import edu.coderhouse.jpa.repositories.ProductRepository;
import edu.coderhouse.jpa.services.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetails) {
        Product product = invoiceDetails.getProduct();

        if (product.getStock() < invoiceDetails.getAmount()) {
            throw new IllegalArgumentException("No hay suficiente stock del producto" + product.getDescription());
        }
        product.setStock(product.getStock() - invoiceDetails.getAmount());
        productRepository.save(product);

        return invoiceDetailsRepository.save(invoiceDetails);
    }
    @Override
    public InvoiceDetails getInvoiceDetailsById(Integer id) {
        Optional<InvoiceDetails> result = invoiceDetailsRepository.findById(id);
        return result.orElse(null);
    }
    @Override
    public List<InvoiceDetails> getAllInvoiceDetails() {
        return invoiceDetailsRepository.findAll();
    }
    @Override
    public InvoiceDetails updateInvoiceDetails(Integer id, InvoiceDetails invoiceDetails) {
        InvoiceDetails existingInvoiceDetails = invoiceDetailsRepository.findById(id).orElse(null);
        if (existingInvoiceDetails != null) {
            existingInvoiceDetails.setInvoice(invoiceDetails.getInvoice());
            existingInvoiceDetails.setProduct(invoiceDetails.getProduct());
            existingInvoiceDetails.setAmount(invoiceDetails.getAmount());
            existingInvoiceDetails.setPrice(invoiceDetails.getPrice());
            return invoiceDetailsRepository.save(existingInvoiceDetails);
        }
        return null;
    }
    @Override
    public InvoiceDetails partialUpdateInvoiceDetails(Integer id, Map<String, Object> updates) {
        InvoiceDetails existingInvoiceDetails = invoiceDetailsRepository.findById(id).orElse(null);
        if (existingInvoiceDetails != null) {
            updates.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(InvoiceDetails.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingInvoiceDetails, value);
                }
            });
            return invoiceDetailsRepository.save(existingInvoiceDetails);
        }
        return null;
    }
    @Override
    public void deleteInvoiceDetails(Integer id) {
        invoiceDetailsRepository.deleteById(id);
    }
}
