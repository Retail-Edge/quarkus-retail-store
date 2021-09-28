package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

/**
   * Entity storing Invoice data.  This class is essentially a wrapper for the InvoiceHeader and InvoiceLines, the combination of which make up an Invoice.
   *
   * @see InvoiceHeader
   * @see InvoiceLine
   *
   */
@Entity
class InvoiceRecord extends PanacheEntity {

    /**
     * Unique id
     */
    @Column(name = "invoice_id", nullable = false, unique = true)
    String invoiceId;

    /**
     *
     */
    @OneToOne @Cascade(org.hibernate.annotations.CascadeType.ALL)
    InvoiceHeader invoiceHeader;

    @OneToMany @Cascade(org.hibernate.annotations.CascadeType.ALL)
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
