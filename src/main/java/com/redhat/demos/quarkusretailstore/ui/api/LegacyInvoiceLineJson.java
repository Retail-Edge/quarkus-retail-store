package com.redhat.demos.quarkusretailstore.ui.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.redhat.demos.quarkusretailstore.invoicing.UnitOfMeasure;

/**
 * v1 API
 */
public class LegacyInvoiceLineJson {

    final ProductMasterJson productMasterJson;

    final Double billQuantity;

    final Double unitPrice;

    final UnitOfMeasure unitOfMeasure;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LegacyInvoiceLineJson(@JsonProperty("productMaster") ProductMasterJson productMasterJson, @JsonProperty("billQuantity") Double billQuantity, @JsonProperty("unitPrice") Double unitPrice, @JsonProperty("unitOfMeasure") UnitOfMeasure unitOfMeasure) {

        this.productMasterJson = productMasterJson;
        this.billQuantity = billQuantity;
        this.unitOfMeasure = unitOfMeasure;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LegacyInvoiceLineJson{");
        sb.append("productMasterJson=").append(productMasterJson);
        sb.append(", billQuantity=").append(billQuantity);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", unitOfMeasure=").append(unitOfMeasure);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegacyInvoiceLineJson that = (LegacyInvoiceLineJson) o;

        if (productMasterJson != null ? !productMasterJson.equals(that.productMasterJson) : that.productMasterJson != null)
            return false;
        if (billQuantity != null ? !billQuantity.equals(that.billQuantity) : that.billQuantity != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;
        return unitOfMeasure == that.unitOfMeasure;
    }

    @Override
    public int hashCode() {
        int result = productMasterJson != null ? productMasterJson.hashCode() : 0;
        result = 31 * result + (billQuantity != null ? billQuantity.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (unitOfMeasure != null ? unitOfMeasure.hashCode() : 0);
        return result;
    }

    public ProductMasterJson getProductMasterJson() {
        return productMasterJson;
    }

    public Double getBillQuantity() {
        return billQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }
}
