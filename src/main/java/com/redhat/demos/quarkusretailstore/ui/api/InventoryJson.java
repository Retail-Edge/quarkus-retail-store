package com.redhat.demos.quarkusretailstore.ui.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class InventoryJson {

    public final String skuId;

    public final Double unitCost;

    public final Double maxRetailPrice;

    public final int orderQuantity;

    public final int inStockQuantity;

    public final int backOrderQuantity;

    public final Date lastStockDate;

    public final Date lastSaleDate;

    public final int minimumQuantity;

    public final int maximumQuantity;

    final int reservedQuantity;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InventoryJson(@JsonProperty("skuId") String skuId, @JsonProperty("unitCost") Double unitCost, @JsonProperty("maxRetailPrice") Double maxRetailPrice, @JsonProperty("orderQuantity") int orderQuantity, @JsonProperty("inStockQuantity") int inStockQuantity, @JsonProperty("backOrderQuantity") int backOrderQuantity, @JsonProperty("lastStockDate") Date lastStockDate, @JsonProperty("lastSaleDate") Date lastSaleDate, @JsonProperty("minimumQuantity") int minimumQuantity, @JsonProperty("maximumQuantity") int maximumQuantity, @JsonProperty("reservedQuantity") int reservedQuantity) {
        this.skuId = skuId;
        this.unitCost = unitCost;
        this.maxRetailPrice = maxRetailPrice;
        this.orderQuantity = orderQuantity;
        this.inStockQuantity = inStockQuantity;
        this.backOrderQuantity = backOrderQuantity;
        this.lastStockDate = lastStockDate;
        this.lastSaleDate = lastSaleDate;
        this.minimumQuantity = minimumQuantity;
        this.maximumQuantity = maximumQuantity;
        this.reservedQuantity = reservedQuantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryJson{");
        sb.append("skuId=").append(skuId);
        sb.append(", unitCost=").append(unitCost);
        sb.append(", maxRetailPrice=").append(maxRetailPrice);
        sb.append(", orderQuantity=").append(orderQuantity);
        sb.append(", inStockQuantity=").append(inStockQuantity);
        sb.append(", backOrderQuantity=").append(backOrderQuantity);
        sb.append(", lastStockDate=").append(lastStockDate);
        sb.append(", lastSaleDate=").append(lastSaleDate);
        sb.append(", minimumQuantity=").append(minimumQuantity);
        sb.append(", maximumQuantity=").append(maximumQuantity);
        sb.append(", reservedQuantity=").append(reservedQuantity);
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
        if (reservedQuantity != that.reservedQuantity) return false;
        if (skuId != null ? !skuId.equals(that.skuId) : that.skuId != null)
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
        int result = skuId != null ? skuId.hashCode() : 0;
        result = 31 * result + (unitCost != null ? unitCost.hashCode() : 0);
        result = 31 * result + (maxRetailPrice != null ? maxRetailPrice.hashCode() : 0);
        result = 31 * result + orderQuantity;
        result = 31 * result + inStockQuantity;
        result = 31 * result + backOrderQuantity;
        result = 31 * result + (lastStockDate != null ? lastStockDate.hashCode() : 0);
        result = 31 * result + (lastSaleDate != null ? lastSaleDate.hashCode() : 0);
        result = 31 * result + minimumQuantity;
        result = 31 * result + maximumQuantity;
        result = 31 * result + reservedQuantity;
        return result;
    }

    public String getSkuId() {
        return skuId;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public Double getMaxRetailPrice() {
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

    public int getReservedQuantity() {
        return reservedQuantity;
    }
}
