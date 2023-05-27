package com.kodlamaio.invoice.business.kafka;

import com.kodlamaio.commonpackage.utils.dto.PaymentCarResponse;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.invoice.business.abstracts.InvoiceService;
import com.kodlamaio.invoice.entities.Invoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private final InvoiceService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "invoice-created",
            groupId = "invoice-inventory-create"
    )
    public void consume(PaymentCarResponse event) {
        Invoice invoice = mapper.forRequest().map(event,Invoice.class);
        service.add(invoice);
        log.info("Invoice created event consumed {}", event);
    }
}
