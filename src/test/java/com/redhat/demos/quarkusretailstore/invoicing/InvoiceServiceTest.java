package com.redhat.demos.quarkusretailstore.invoicing;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceHeaderDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceLineDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceService;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.testing.TestValues;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class InvoiceServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceTest.class);

    @Inject
    InvoiceService invoiceService;

    @BeforeEach
    @Transactional
    public void setUp() {
        ProductMaster productMaster = new ProductMaster(TestValues.SKU_ID_02, "A Product");
        productMaster.persistAndFlush();
    }

    @Test
    public void testInvoicing() {

        InvoiceDTO invoiceDTO = new InvoiceDTO(
                TestValues.INVOICE_ID_02,
                new InvoiceHeaderDTO(TestValues.STORE_ID, Calendar.getInstance().getTime(), 21.99, 1),
                Arrays.asList(new InvoiceLineDTO(TestValues.SKU_ID_02, 1.0, 21.99, 21.99, UnitOfMeasure.EACH)), TestValues.CUSTOMER_NAME
        );

        InvoiceDTO result = invoiceService.createInvoice(invoiceDTO);
        assertEquals(TestValues.INVOICE_ID_02, result.getInvoiceId());
        assertEquals(TestValues.STORE_ID, result.getInvoiceHeader().getStoreId());
        result.getInvoiceLines().forEach(invoiceLineDTO -> {
            assertEquals(TestValues.SKU_ID_02, invoiceLineDTO.getSkuId());
            assertEquals(UnitOfMeasure.EACH, invoiceLineDTO.getUnitOfMeasure());
        });
    }
}
