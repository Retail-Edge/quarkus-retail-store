package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.NoSuchInvoiceException;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class InvoiceRepository implements PanacheRepository<InvoiceRecord> {

    public Invoice persist(Invoice invoice){
        persist(invoice.invoiceRecord);
        return invoice;
    }

}
