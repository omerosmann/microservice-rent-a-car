package com.kodlamaio.invoice.api.controllers;

import com.kodlamaio.invoice.business.abstracts.InvoiceService;
import com.kodlamaio.invoice.business.dto.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoice.business.dto.responses.GetInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    //    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable String id) {
//            service.delete(id);
//    }


}

