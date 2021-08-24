package com.redhat.demos.quarkusretailstore.ui.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Date;

public class CreateInvoiceCommand {

    InvoiceHeaderJson invoiceHeader;

    Collection<InvoiceLineJson> invoiceLines;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CreateInvoiceCommand(@JsonProperty("invoiceHeader") InvoiceHeaderJson invoiceHeader, @JsonProperty("invoiceLines") Collection<InvoiceLineJson> invoiceLines) {
        this.invoiceHeader = invoiceHeader;
        this.invoiceLines = invoiceLines;
    }
}
