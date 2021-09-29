package com.redhat.demos.quarkusretailstore.invoicing.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Contains summary details for an Invoice.
 * The invoice header is contains key aspects of the bill such as the date the invoice should be sent, customer references, currency and any other details
 */
@Entity
public class InvoiceHeader extends PanacheEntity {

    String storeId;

    Date date;

    Double totalDollarAmount;

    int numberOfLines;

    /**
     * Simplification of tax
     * @return
     */
    public Double primaryTax() {
        return totalDollarAmount * 0.07;
    }

    /**
     * Derived amount representing the actual dollar amount of the Invoice
     * @return
     */
    public Double finalInvoiceAmount() {
        return totalDollarAmount + primaryTax();
    }

    public InvoiceHeader() {

    }

    public InvoiceHeader(String storeId, Date date, Double totalDollarAmount, int numberOfLines) {
        this.storeId = storeId;
        this.date = date;
        this.totalDollarAmount = totalDollarAmount;
        this.numberOfLines = numberOfLines;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceHeader{");
        sb.append("storeId='").append(storeId).append('\'');
        sb.append(", date=").append(date);
        sb.append(", totalDollarAmount=").append(totalDollarAmount);
        sb.append(", numberOfLines=").append(numberOfLines);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceHeader that = (InvoiceHeader) o;

        if (id != that.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + totalDollarAmount.hashCode();
        result = 31 * result + numberOfLines;
        return result;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalDollarAmount() {
        return totalDollarAmount;
    }

    public void setTotalDollarAmount(Double totalDollarAmount) {
        this.totalDollarAmount = totalDollarAmount;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }
}
