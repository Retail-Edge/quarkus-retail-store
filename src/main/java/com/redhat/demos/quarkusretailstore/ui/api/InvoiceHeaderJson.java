package com.redhat.demos.quarkusretailstore.ui.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceHeaderDTO;

import java.util.Date;

public class InvoiceHeaderJson {

    final String id;

    final Date date;

    final Double totalDollarAmount;

    final int numberOfLines;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InvoiceHeaderJson(@JsonProperty("id") String id, @JsonProperty("date") Date date, @JsonProperty("totalDollarAmount") Double totalDollarAmount, @JsonProperty("numberOfLines") int numberOfLines) {
        this.id = id;
        this.date = date;
        this.totalDollarAmount = totalDollarAmount;
        this.numberOfLines = numberOfLines;
    }

    public InvoiceHeaderJson(final InvoiceHeaderDTO invoiceHeaderDTO) {

        this.id = invoiceHeaderDTO.getId();
        this.date = invoiceHeaderDTO.getDate();
        this.totalDollarAmount = invoiceHeaderDTO.getTotalDollarAmount();
        this.numberOfLines = invoiceHeaderDTO.getNumberOfLines();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceHeaderJson{");
        sb.append("id='").append(id).append('\'');
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

        InvoiceHeaderJson that = (InvoiceHeaderJson) o;

        if (numberOfLines != that.numberOfLines) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return totalDollarAmount != null ? totalDollarAmount.equals(that.totalDollarAmount) : that.totalDollarAmount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (totalDollarAmount != null ? totalDollarAmount.hashCode() : 0);
        result = 31 * result + numberOfLines;
        return result;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Double getTotalDollarAmount() {
        return totalDollarAmount;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }
}
