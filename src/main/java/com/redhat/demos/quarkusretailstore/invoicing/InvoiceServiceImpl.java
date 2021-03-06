package com.redhat.demos.quarkusretailstore.invoicing;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceHeaderDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceLineDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceService;
import com.redhat.demos.quarkusretailstore.invoicing.domain.Invoice;
import com.redhat.demos.quarkusretailstore.invoicing.domain.InvoiceEventResult;
import com.redhat.demos.quarkusretailstore.invoicing.domain.InvoiceRepository;
import com.redhat.demos.quarkusretailstore.products.NoSuchProductException;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.api.ProductsService;
import io.debezium.outbox.quarkus.ExportedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Inject
    InvoiceRepository invoiceRepository;

    @Inject
    Event<ExportedEvent<?, ?>> event;

    @Override @Transactional
    public InvoiceDTO createInvoice(final InvoiceDTO invoiceDTO) {

        LOGGER.debug("creating invoice for: {}", invoiceDTO);
        LOGGER.debug("looking up products");
        HashMap<String, ProductMaster> products = new HashMap<>(invoiceDTO.getInvoiceLines().size());
        invoiceDTO.getInvoiceLines().forEach(invoiceLineDTO -> {
            ProductMaster productMaster = ProductMaster.findBySkuId(invoiceLineDTO.getSkuId());
            products.put(productMaster.getSkuId(), productMaster);
            LOGGER.debug("added product: {}",  productMaster);
        });

        InvoiceEventResult  invoiceEventResult = Invoice.create(invoiceDTO, products);
        LOGGER.debug("invoice created: {}", invoiceEventResult.getInvoice());
        Invoice invoice = invoiceRepository.persist(invoiceEventResult.getInvoice());
        LOGGER.debug("invoice persisted {}", invoice);
        invoiceEventResult.getInvoiceEvents().forEach(invoiceEvent -> {
            event.fire(invoiceEvent);
        });
        return new InvoiceDTO(
                invoice.getId(),
                new InvoiceHeaderDTO(invoice.getInvoiceHeader()),
                invoice.getInvoiceLines().stream().map(invoiceLine -> {
                    return new InvoiceLineDTO(invoiceLine.getProductMaster().getSkuId(), invoiceLine.getBillQuantity(), invoiceLine.getUnitPrice(), invoiceLine.getExtendedPrice(), invoiceLine.getUnitOfMeasure());}).collect(Collectors.toList()),
                invoice.getCustomer());
    }

    @Override
    public InvoiceDTO findById(String id) throws NoSuchInvoiceException {
        throw new NoSuchInvoiceException("not implemented");
    }
}
