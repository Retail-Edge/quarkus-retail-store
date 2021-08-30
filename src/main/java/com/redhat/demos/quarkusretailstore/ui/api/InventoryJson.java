package com.redhat.demos.quarkusretailstore.ui.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class InventoryJson {

    public final ProductMasterDTO productMaster;

    public final BigDecimal unitCost;

    public final BigDecimal maxRetailPrice;

    public final int orderQuantity;

    public final int inStockQuantity;

    public final int backOrderQuantity;

    public final Date lastStockDate;

    public final Date lastSaleDate;

    public final int minimumQuantity;

    public final int maximumQuantity;

    public InventoryJson(ProductMasterDTO productMaster, BigDecimal unitCost, BigDecimal maxRetailPrice, int orderQuantity, int inStockQuantity, int backOrderQuantity, LocalDateTime lastStockDate, LocalDateTime lastSaleDate, int minimumQuantity, int maximumQuantity) {
        this.productMaster = productMaster;
        this.unitCost = unitCost;
        this.maxRetailPrice = maxRetailPrice;
        this.orderQuantity = orderQuantity;
        this.inStockQuantity = inStockQuantity;
        this.backOrderQuantity = backOrderQuantity;
        this.lastStockDate = Date.from(lastStockDate.toLocalDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        this.lastSaleDate = Date.from(lastSaleDate.toLocalDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        this.minimumQuantity = minimumQuantity;
        this.maximumQuantity = maximumQuantity;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InventoryJson(@JsonProperty("productMaster") ProductMasterDTO productMaster, @JsonProperty("unitCost") BigDecimal unitCost, @JsonProperty("maxRetailPrice") BigDecimal maxRetailPrice, @JsonProperty("orderQuantity") int orderQuantity, @JsonProperty("inStockQuantity") int inStockQuantity, @JsonProperty("backOrderQuantity") int backOrderQuantity, @JsonProperty("lastStockDate") Date lastStockDate, @JsonProperty("lastSaleDate") Date lastSaleDate, @JsonProperty("minimumQuantity") int minimumQuantity, @JsonProperty("maximumQuantity") int maximumQuantity) {
        this.productMaster = productMaster;
        this.unitCost = unitCost;
        this.maxRetailPrice = maxRetailPrice;
        this.orderQuantity = orderQuantity;
        this.inStockQuantity = inStockQuantity;
        this.backOrderQuantity = backOrderQuantity;
        this.lastStockDate = lastStockDate;
        this.lastSaleDate = lastSaleDate;
        this.minimumQuantity = minimumQuantity;
        this.maximumQuantity = maximumQuantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryJson{");
        sb.append("productMaster=").append(productMaster);
        sb.append(", unitCost=").append(unitCost);
        sb.append(", maxRetailPrice=").append(maxRetailPrice);
        sb.append(", orderQuantity=").append(orderQuantity);
        sb.append(", inStockQuantity=").append(inStockQuantity);
        sb.append(", backOrderQuantity=").append(backOrderQuantity);
        sb.append(", lastStockDate=").append(lastStockDate);
        sb.append(", lastSaleDate=").append(lastSaleDate);
        sb.append(", minimumQuantity=").append(minimumQuantity);
        sb.append(", maximumQuantity=").append(maximumQuantity);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryJson that = (InventoryJson) o;

        if (orderQuantity != that.orderQuantity) return false;
        if (inStockQuantity != that.inStockQuantity) return false;
        if (backOrderQuantity != that.backOrderQuantity) return false;
        if (minimumQuantity != that.minimumQuantity) return false;
        if (maximumQuantity != that.maximumQuantity) return false;
        if (productMaster != null ? !productMaster.equals(that.productMaster) : that.productMaster != null)
            return false;
        if (unitCost != null ? !unitCost.equals(that.unitCost) : that.unitCost != null) return false;
        if (maxRetailPrice != null ? !maxRetailPrice.equals(that.maxRetailPrice) : that.maxRetailPrice != null)
            return false;
        if (lastStockDate != null ? !lastStockDate.equals(that.lastStockDate) : that.lastStockDate != null)
            return false;
        return lastSaleDate != null ? lastSaleDate.equals(that.lastSaleDate) : that.lastSaleDate == null;
    }

    @Override
    public int hashCode() {
        int result = productMaster != null ? productMaster.hashCode() : 0;
        result = 31 * result + (unitCost != null ? unitCost.hashCode() : 0);
        result = 31 * result + (maxRetailPrice != null ? maxRetailPrice.hashCode() : 0);
        result = 31 * result + orderQuantity;
        result = 31 * result + inStockQuantity;
        result = 31 * result + backOrderQuantity;
        result = 31 * result + (lastStockDate != null ? lastStockDate.hashCode() : 0);
        result = 31 * result + (lastSaleDate != null ? lastSaleDate.hashCode() : 0);
        result = 31 * result + minimumQuantity;
        result = 31 * result + maximumQuantity;
        return result;
    }

    public ProductMasterDTO getProductMaster() {
        return productMaster;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public BigDecimal getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public int getInStockQuantity() {
        return inStockQuantity;
    }

    public int getBackOrderQuantity() {
        return backOrderQuantity;
    }

    public Date getLastStockDate() {
        return lastStockDate;
    }

    public Date getLastSaleDate() {
        return lastSaleDate;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public int getMaximumQuantity() {
        return maximumQuantity;
    }
}
