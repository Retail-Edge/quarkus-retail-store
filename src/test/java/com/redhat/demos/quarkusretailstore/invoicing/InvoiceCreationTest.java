package com.redhat.demos.quarkusretailstore.invoicing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceHeaderDTO;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceLineDTO;
import com.redhat.demos.quarkusretailstore.invoicing.domain.Invoice;
import com.redhat.demos.quarkusretailstore.invoicing.domain.InvoiceEventResult;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceCreationTest {

    @Test
    public void testCreatingInvoice() {

        InvoiceHeaderDTO invoiceHeaderDTO = new InvoiceHeaderDTO(UUID.randomUUID().toString(), Calendar.getInstance().getTime(), 19.99, 1);
        InvoiceDTO invoiceDTO = new InvoiceDTO(UUID.randomUUID().toString(), invoiceHeaderDTO, Arrays.asList(new InvoiceLineDTO(new ProductMaster(UUID.randomUUID().toString(), "A product"), BigDecimal.valueOf(1), 19.99, BigDecimal.valueOf(19.99), UnitOfMeasure.EACH)), "Moe");
        InvoiceEventResult invoiceEventResult = Invoice.create(invoiceDTO);
        assertNotNull(invoiceEventResult);
        assertNotNull(invoiceEventResult.getInvoice());
        assertNotNull(invoiceEventResult.getInvoiceEvents());
    }
}
