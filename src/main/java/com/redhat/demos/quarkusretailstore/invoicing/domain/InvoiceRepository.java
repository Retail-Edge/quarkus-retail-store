package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.NoSuchInvoiceException;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@ApplicationScoped
public class InvoiceRepository implements PanacheRepository<InvoiceRecord> {

    public Invoice persist(Invoice invoice){
        Collection<ProductMaster> productMasters = new ArrayList<>(invoice.invoiceRecord.getInvoiceLines().size());
        invoice.invoiceRecord.invoiceLines.forEach(invoiceLine -> {
            productMasters.add(ProductMaster.findBySkuId(invoiceLine.getProductMaster().getSkuId()));
        });
        persist(invoice.invoiceRecord);
        return invoice;
    }

}
