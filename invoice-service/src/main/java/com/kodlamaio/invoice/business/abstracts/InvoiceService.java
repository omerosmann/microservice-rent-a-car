package com.kodlamaio.invoice.business.abstracts;

import com.kodlamaio.invoice.business.dto.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoice.business.dto.responses.GetInvoiceResponse;
import com.kodlamaio.invoice.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(String id);

    void add(Invoice invoice);
    void delete(String id);


}
