package com.kodlamaio.invoice.business.concretes;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.invoice.business.abstracts.InvoiceService;
import com.kodlamaio.invoice.business.dto.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoice.business.dto.responses.GetInvoiceResponse;
import com.kodlamaio.invoice.entities.Invoice;
import com.kodlamaio.invoice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        var invoices = repository.findAll();
        var response = invoices.stream()
                .map(invoice -> mapper.forResponse().map(invoice,GetAllInvoicesResponse.class)).toList();

        return response;
    }

    @Override
    public GetInvoiceResponse getById(String id) {
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.forResponse().map(invoice,GetInvoiceResponse.class);

        return response;
    }

    @Override
    public void add(Invoice invoice) {
        repository.save(invoice);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
