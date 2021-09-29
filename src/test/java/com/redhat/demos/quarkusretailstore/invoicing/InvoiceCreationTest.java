package com.redhat.demos.quarkusretailstore.invoicing;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceHeaderDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceLineDTO;
import com.redhat.demos.quarkusretailstore.invoicing.domain.Invoice;
import com.redhat.demos.quarkusretailstore.invoicing.domain.InvoiceEventResult;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class InvoiceCreationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceCreationTest.class);

    @Test
    public void testCreatingInvoice() {

        InvoiceHeaderDTO invoiceHeaderDTO = new InvoiceHeaderDTO(UUID.randomUUID().toString(), Calendar.getInstance().getTime(), 19.99, 1);
        ProductMaster productMaster = new ProductMaster(UUID.randomUUID().toString(), "Product description");
        Map<String, ProductMaster> productMasters = Collections.singletonMap(productMaster.getSkuId(),productMaster);
        InvoiceDTO invoiceDTO = new InvoiceDTO(UUID.randomUUID().toString(), invoiceHeaderDTO, Arrays.asList(new InvoiceLineDTO(UUID.randomUUID().toString(), Double.valueOf(1), 19.99, Double.valueOf(19.99), UnitOfMeasure.EACH)), "Moe");
        LOGGER.info("Testing invoice creation with: {}", invoiceDTO);

        InvoiceEventResult invoiceEventResult = Invoice.create(invoiceDTO, productMasters);
        LOGGER.info("InvoiceEventResult: {}", invoiceEventResult);
        assertNotNull(invoiceEventResult);
        assertNotNull(invoiceEventResult.getInvoice());
        assertNotNull(invoiceEventResult.getInvoice().getInvoiceLines());
        assertEquals(1, invoiceEventResult.getInvoice().getInvoiceLines().size());
        assertNotNull(invoiceEventResult.getInvoiceEvents());
    }
}
