package com.redhat.demos.quarkusretailstore.invoicing.domain;

import java.util.Collection;

/**
 * Value object containing an InvoiceRecord and associated ExportedEvent objects
 */
public class InvoiceEventResult {

    private Invoice invoice;

    private Collection<InvoiceEvent> invoiceEvents;

    public InvoiceEventResult(Invoice invoice, Collection<InvoiceEvent> invoiceEvents) {
        this.invoice = invoice;
        this.invoiceEvents = invoiceEvents;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceEventResult{");
        sb.append("invoice=").append(invoice);
        sb.append(", invoiceEvents=").append(invoiceEvents);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceEventResult that = (InvoiceEventResult) o;

        if (invoice != null ? !invoice.equals(that.invoice) : that.invoice != null) return false;
        return invoiceEvents != null ? invoiceEvents.equals(that.invoiceEvents) : that.invoiceEvents == null;
    }

    @Override
    public int hashCode() {
        int result = invoice != null ? invoice.hashCode() : 0;
        result = 31 * result + (invoiceEvents != null ? invoiceEvents.hashCode() : 0);
        return result;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Collection<InvoiceEvent> getInvoiceEvents() {
        return invoiceEvents;
    }

    public void setInvoiceEvents(Collection<InvoiceEvent> invoiceEvents) {
        this.invoiceEvents = invoiceEvents;
    }
}
