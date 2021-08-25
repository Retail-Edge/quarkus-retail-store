package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Collection;

/**
 * Entity storing Invoice data
 */
@Entity
class InvoiceRecord extends PanacheEntity {

    @Column(name = "invoice_id", nullable = false, unique = true) @GeneratedValue(strategy = GenerationType.IDENTITY)
    String invoiceId;

    @OneToOne
    InvoiceHeader invoiceHeader;

    @OneToMany
    Collection<InvoiceLine> invoiceLines;

    String customerName;

    public InvoiceRecord() {

    }

    public InvoiceRecord(String invoiceId, InvoiceHeader invoiceHeader, Collection<InvoiceLine> invoiceLines, String customerName) {
        this.invoiceId = invoiceId;
        this.invoiceHeader = invoiceHeader;
        this.invoiceLines = invoiceLines;
        this.customerName = customerName;
    }

    public InvoiceRecord(final InvoiceDTO invoiceDTO) {

    }

    public InvoiceRecord(final String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceRecord{");
        sb.append("customerName='").append(customerName).append('\'');
        sb.append(", id=").append(invoiceId);
        sb.append('}');
        return sb.toString();
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public Collection<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(Collection<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
