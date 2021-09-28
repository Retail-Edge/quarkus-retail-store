package com.redhat.demos.quarkusretailstore.invoicing;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceHeaderDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceLineDTO;
import com.redhat.demos.quarkusretailstore.invoicing.domain.Invoice;
import com.redhat.demos.quarkusretailstore.invoicing.domain.InvoiceEventResult;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceCreationTest {

    @Test
    public void testCreatingInvoice() {

        InvoiceHeaderDTO invoiceHeaderDTO = new InvoiceHeaderDTO(UUID.randomUUID().toString(), Calendar.getInstance().getTime(), 19.99, 1);
        ProductMaster productMaster = new ProductMaster(UUID.randomUUID().toString(), "Product description");
        Map<String, ProductMaster> productMasters = Collections.singletonMap(productMaster.getSkuId(),productMaster);
        InvoiceDTO invoiceDTO = new InvoiceDTO(UUID.randomUUID().toString(), invoiceHeaderDTO, Arrays.asList(new InvoiceLineDTO(UUID.randomUUID().toString(), Double.valueOf(1), 19.99, Double.valueOf(19.99), UnitOfMeasure.EACH)), "Moe");

        InvoiceEventResult invoiceEventResult = Invoice.create(invoiceDTO, productMasters);
        assertNotNull(invoiceEventResult);
        assertNotNull(invoiceEventResult.getInvoice());
        assertNotNull(invoiceEventResult.getInvoiceEvents());
    }
}
