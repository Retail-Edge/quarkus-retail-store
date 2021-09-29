package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.UnitOfMeasure;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity storing each line item of an Invoice
 */
@Entity
public class InvoiceLine extends PanacheEntity {

    /**
     * The product
     */
    @ManyToOne(optional = false)
    @JoinColumn(
            name = "skuid",
            referencedColumnName = "skuid"
    )
    ProductMaster productMaster;

    /**
     * The quantity of product sold to the customer, or a negative quantity for a return
     */
    Double billQuantity;

    /**
     * The actual price paid by the customer.  Typically determined by a rules engine.
     */
    Double unitPrice;

    /**
     * Unit of sale
     */
    UnitOfMeasure unitOfMeasure;

    /**
     * Derived billQuantity * unitPrice
     *
     * @return
     */
    public Double getExtendedPrice() {

        return billQuantity * unitPrice;
    }

    public InvoiceLine() {

    }

    public InvoiceLine(ProductMaster productMaster, Double billQuantity, Double unitPrice, UnitOfMeasure unitOfMeasure) {
        this.productMaster = productMaster;
        this.billQuantity = billQuantity;
        this.unitPrice = unitPrice;
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceLine that = (InvoiceLine) o;

        if (productMaster != null ? !productMaster.equals(that.productMaster) : that.productMaster != null)
            return false;
        if (billQuantity != null ? !billQuantity.equals(that.billQuantity) : that.billQuantity != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;
        return unitOfMeasure == that.unitOfMeasure;
    }

    @Override
    public int hashCode() {
        int result = productMaster != null ? productMaster.hashCode() : 0;
        result = 31 * result + (billQuantity != null ? billQuantity.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (unitOfMeasure != null ? unitOfMeasure.hashCode() : 0);
        return result;
    }

    public ProductMaster getProductMaster() {
        return productMaster;
    }

    public void setProductMaster(ProductMaster productMaster) {
        this.productMaster = productMaster;
    }

    public Double getBillQuantity() {
        return billQuantity;
    }

    public void setBillQuantity(Double billQuantity) {
        this.billQuantity = billQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
