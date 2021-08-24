package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceHeaderDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InvoiceHeader extends PanacheEntity {

    String storeId;

    Date date;

    Double totalDollarAmount;

    int numberOfLines;

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
        sb.append("id='").append(id).append('\'');
        sb.append(", storeId='").append(storeId).append('\'');
        sb.append(", date=").append(date);
        sb.append(", totalDollarAmount=").append(totalDollarAmount);
        sb.append(", numberOfLines=").append(numberOfLines);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceHeader that = (InvoiceHeader) o;

        if (numberOfLines != that.numberOfLines) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (storeId != null ? !storeId.equals(that.storeId) : that.storeId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return totalDollarAmount != null ? totalDollarAmount.equals(that.totalDollarAmount) : that.totalDollarAmount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (storeId != null ? storeId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (totalDollarAmount != null ? totalDollarAmount.hashCode() : 0);
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
