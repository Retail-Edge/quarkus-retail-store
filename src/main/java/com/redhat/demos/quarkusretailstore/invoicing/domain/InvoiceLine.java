package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.UnitOfMeasure;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class InvoiceLine extends PanacheEntity {

    String skuId;

    String productDescripiton;

    BigDecimal billQuantity;

    Double unitPrice;

    BigDecimal extendedPrice;

    UnitOfMeasure unitOfMeasure;

    public InvoiceLine() {

    }

    public InvoiceLine(String skuId, String productDescripiton, BigDecimal billQuantity, Double unitPrice, BigDecimal extendedPrice, UnitOfMeasure unitOfMeasure) {
        this.skuId = skuId;
        this.productDescripiton = productDescripiton;
        this.billQuantity = billQuantity;
        this.unitPrice = unitPrice;
        this.extendedPrice = extendedPrice;
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceLine that = (InvoiceLine) o;

        if (billQuantity != that.billQuantity) return false;
        if (skuId != null ? !skuId.equals(that.skuId) : that.skuId != null) return false;
        if (productDescripiton != null ? !productDescripiton.equals(that.productDescripiton) : that.productDescripiton != null)
            return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;
        if (extendedPrice != null ? !extendedPrice.equals(that.extendedPrice) : that.extendedPrice != null)
            return false;
        return unitOfMeasure == that.unitOfMeasure;
    }

    @Override
    public int hashCode() {
        int result = skuId != null ? skuId.hashCode() : 0;
        result = 31 * result + (productDescripiton != null ? productDescripiton.hashCode() : 0);
        result = 31 * result + billQuantity.intValue();
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (extendedPrice != null ? extendedPrice.hashCode() : 0);
        result = 31 * result + (unitOfMeasure != null ? unitOfMeasure.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceLine{");
        sb.append("skuId='").append(skuId).append('\'');
        sb.append(", productDescripiton='").append(productDescripiton).append('\'');
        sb.append(", billQuantity=").append(billQuantity);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", extendedPrice=").append(extendedPrice);
        sb.append(", unitOfMeasure=").append(unitOfMeasure);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductDescripiton() {
        return productDescripiton;
    }

    public void setProductDescripiton(String productDescripiton) {
        this.productDescripiton = productDescripiton;
    }

    public BigDecimal getBillQuantity() {
        return billQuantity;
    }

    public void setBillQuantity(BigDecimal billQuantity) {
        this.billQuantity = billQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(BigDecimal extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
