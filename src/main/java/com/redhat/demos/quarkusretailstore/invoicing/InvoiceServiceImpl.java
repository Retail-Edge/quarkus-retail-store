package com.redhat.demos.quarkusretailstore.invoicing;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceService;
import com.redhat.demos.quarkusretailstore.invoicing.domain.Invoice;
import com.redhat.demos.quarkusretailstore.invoicing.domain.InvoiceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InvoiceServiceImpl implements InvoiceService {

    @Inject
    InvoiceRepository invoiceRepository;

    @Override
    public InvoiceDTO createInvoice(final InvoiceDTO invoiceDTO) {

        Invoice invoice = new Invoice(invoiceDTO);
        return invoiceDTO;
    }

    @Override
    public InvoiceDTO findById(String id) throws NoSuchInvoiceException {
        return null;
    }
}
