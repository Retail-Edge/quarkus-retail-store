package com.redhat.demos.quarkusretailstore.ui.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * JSON representation of an Invoice
 */
public class InvoiceJson {

    final String invoiceId;

    final InvoiceHeaderJson invoiceHeader;

    final Collection<InvoiceLineJson> invoiceLines;

    final String customerName;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InvoiceJson(@JsonProperty("invoiceId") String invoiceId, @JsonProperty("invoiceHeader") InvoiceHeaderJson invoiceHeader, @JsonProperty("invoiceLines") Collection<InvoiceLineJson> invoiceLines, @JsonProperty("customerName") String customerName) {
        this.invoiceId = invoiceId;
        this.invoiceHeader = invoiceHeader;
        this.invoiceLines = invoiceLines;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceJson{");
        sb.append("invoiceId='").append(invoiceId).append('\'');
        sb.append(", invoiceHeader=").append(invoiceHeader);
        sb.append(", invoiceLines=").append(invoiceLines);
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceJson that = (InvoiceJson) o;

        if (invoiceId != null ? !invoiceId.equals(that.invoiceId) : that.invoiceId != null) return false;
        if (invoiceHeader != null ? !invoiceHeader.equals(that.invoiceHeader) : that.invoiceHeader != null)
            return false;
        if (invoiceLines != null ? !invoiceLines.equals(that.invoiceLines) : that.invoiceLines != null) return false;
        return customerName != null ? customerName.equals(that.customerName) : that.customerName == null;
    }

    @Override
    public int hashCode() {
        int result = invoiceId != null ? invoiceId.hashCode() : 0;
        result = 31 * result + (invoiceHeader != null ? invoiceHeader.hashCode() : 0);
        result = 31 * result + (invoiceLines != null ? invoiceLines.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        return result;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public InvoiceHeaderJson getInvoiceHeader() {
        return invoiceHeader;
    }

    public Collection<InvoiceLineJson> getInvoiceLines() {
        return invoiceLines;
    }

    public String getCustomerName() {
        return customerName;
    }
}
