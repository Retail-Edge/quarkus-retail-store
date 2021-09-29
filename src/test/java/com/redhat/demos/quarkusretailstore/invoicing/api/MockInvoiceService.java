package com.redhat.demos.quarkusretailstore.invoicing.api;

import com.redhat.demos.quarkusretailstore.invoicing.NoSuchInvoiceException;
import com.redhat.demos.quarkusretailstore.invoicing.UnitOfMeasure;
import com.redhat.demos.quarkusretailstore.testing.TestValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MockInvoiceService implements InvoiceService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MockInvoiceService.class);

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

        LOGGER.info("createInvoice called with: {}", invoiceDTO);
        assertNotNull(invoiceDTO.getInvoiceLines());
        assertNotNull(invoiceDTO.invoiceId);
        assertEquals(TestValues.INVOICE_ID, invoiceDTO.invoiceId);
        assertNotNull(invoiceDTO.getInvoiceId());
        assertNotNull(invoiceDTO.getInvoiceHeader());
        assertNotNull(invoiceDTO.customerName);
        assertNotNull(invoiceDTO.getInvoiceLines());
        invoiceDTO.getInvoiceLines().forEach(invoiceLineDTO -> {
            assertNotNull(invoiceLineDTO.billQuantity);
            assertNotNull(invoiceLineDTO.extendedPrice);
            assertNotNull(invoiceLineDTO.skuId);
            assertNotNull(invoiceLineDTO.unitPrice);
            assertNotNull(invoiceLineDTO.unitOfMeasure);
            assertEquals(UnitOfMeasure.EACH, invoiceLineDTO.unitOfMeasure);
        });

        assertEquals(TestValues.STORE_ID, invoiceDTO.getInvoiceHeader().storeId);
        return invoiceDTO;
    }

    @Override
    public InvoiceDTO findById(String id) throws NoSuchInvoiceException {
        return null;
    }
}
