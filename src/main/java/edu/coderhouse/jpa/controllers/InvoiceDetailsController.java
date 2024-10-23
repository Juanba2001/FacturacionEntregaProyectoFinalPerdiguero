package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.entities.InvoiceDetails;
import edu.coderhouse.jpa.services.InvoiceDetailsService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-details")
public class InvoiceDetailsController {
    @Autowired
    private InvoiceDetailsService invoiceDetailsService;
    @PostMapping
    public ResponseEntity<?> createInvoiceDetails(@RequestBody InvoiceDetails invoiceDetails) {
        try {
            InvoiceDetails newInvoiceDetails = invoiceDetailsService.saveInvoiceDetails(invoiceDetails);
            return new ResponseEntity<>(newInvoiceDetails, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetails> getInvoiceDetailsById(@PathVariable Integer id) {
        InvoiceDetails invoiceDetails = invoiceDetailsService.getInvoiceDetailsById(id);
        return invoiceDetails != null ? ResponseEntity.ok(invoiceDetails) : ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<InvoiceDetails>> getAllInvoiceDetails() {
        List<InvoiceDetails> invoiceDetailsList = invoiceDetailsService.getAllInvoiceDetails();
        return ResponseEntity.ok(invoiceDetailsList);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceDetails(@PathVariable Integer id) {
        invoiceDetailsService.deleteInvoiceDetails(id);
        return ResponseEntity.noContent().build();
    }
}
