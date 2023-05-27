package com.kodlamaio.invoice.repository;

import com.kodlamaio.invoice.entities.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
