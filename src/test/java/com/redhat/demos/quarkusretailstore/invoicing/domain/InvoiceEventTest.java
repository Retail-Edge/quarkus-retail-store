package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.UnitOfMeasure;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.testing.TestValues;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceEventTest {

    @Test
    public void testCreatingInvoiceEvent() {

        InvoiceRecord invoiceRecord = new InvoiceRecord(
                TestValues.INVOICE_ID,
                new InvoiceHeader(
                        TestValues.STORE_ID,
                        Calendar.getInstance().getTime(),
                        21.99,
                        1),
                Arrays.asList(new InvoiceLine(
                        new ProductMaster(TestValues.SKU_ID, TestValues.SKU_DESCRIPTION),
                        1.0,
                        21.99,
                        UnitOfMeasure.EACH
                )),
                "Pooh"
        );

        InvoiceEvent invoiceEvent = InvoiceEvent.from(invoiceRecord);
        assertNotNull(invoiceEvent);
        assertNotNull(invoiceEvent.getAggregateId());
        assertNotNull(invoiceEvent.getAggregateType());
        assertNotNull(invoiceEvent.getPayload());
        assertNotNull(invoiceEvent.getTimestamp());
        assertNotNull(invoiceEvent.getType());
    }
}
