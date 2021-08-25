package com.redhat.demos.quarkusretailstore.ui.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.redhat.demos.quarkusretailstore.invoicing.UnitOfMeasure;
import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceLineDTO;

import java.math.BigDecimal;

public class InvoiceLineJson {

    final ProductMasterJson productMaster;

    final BigDecimal billQuantity;

    final Double unitPrice;

    final BigDecimal extendedPrice;

    final UnitOfMeasure unitOfMeasure;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InvoiceLineJson(@JsonProperty("productMaster") ProductMasterJson productMasterJson, @JsonProperty("billQuantity") BigDecimal billQuantity, @JsonProperty("unitPrice") Double unitPrice, @JsonProperty("extendedPrice") BigDecimal extendedPrice, @JsonProperty("unitOfMeasure") UnitOfMeasure unitOfMeasure) {
        this.productMaster = productMasterJson;
        this.billQuantity = billQuantity;
        this.unitPrice = unitPrice;
        this.extendedPrice = extendedPrice;
        this.unitOfMeasure = unitOfMeasure;
    }

    public InvoiceLineJson(final InvoiceLineDTO invoiceLineDTO) {
        this.productMaster = new ProductMasterJson(invoiceLineDTO.getProductMaster().getSkuId(), invoiceLineDTO.getProductMaster().getDescription());
        this.billQuantity = invoiceLineDTO.getBillQuantity();
        this.unitPrice = invoiceLineDTO.getUnitPrice();
        this.extendedPrice = invoiceLineDTO.getExtendedPrice();
        this.unitOfMeasure = invoiceLineDTO.getUnitOfMeasure();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceLineJson{");
        sb.append("productMaster=").append(productMaster);
        sb.append(", billQuantity=").append(billQuantity);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", extendedPrice=").append(extendedPrice);
        sb.append(", unitOfMeasure=").append(unitOfMeasure);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceLineJson that = (InvoiceLineJson) o;

        if (productMaster != null ? !productMaster.equals(that.productMaster) : that.productMaster != null)
            return false;
        if (billQuantity != null ? !billQuantity.equals(that.billQuantity) : that.billQuantity != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;
        if (extendedPrice != null ? !extendedPrice.equals(that.extendedPrice) : that.extendedPrice != null)
            return false;
        return unitOfMeasure == that.unitOfMeasure;
    }

    @Override
    public int hashCode() {
        int result = productMaster != null ? productMaster.hashCode() : 0;
        result = 31 * result + (billQuantity != null ? billQuantity.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (extendedPrice != null ? extendedPrice.hashCode() : 0);
        result = 31 * result + (unitOfMeasure != null ? unitOfMeasure.hashCode() : 0);
        return result;
    }

    public ProductMasterJson getProductMaster() {
        return productMaster;
    }

    public BigDecimal getBillQuantity() {
        return billQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getExtendedPrice() {
        return extendedPrice;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }
}
