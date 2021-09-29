package com.redhat.demos.quarkusretailstore.inventory.api;

import java.util.Date;

public class InventoryDTO {

    final String skuId;

    final Double unitCost;

    final Double maxRetailPrice;

    final int orderQuantity;

    final int inStockQuantity;

    final int backOrderQuantity;

    final Date lastStockDate;

    final Date lastSaleDate;

    final int minimumQuantity;

    final int maximumQuantity;

    final int reservedQuantity;

    final int availableQuantity;

    public InventoryDTO(String skuId, Double unitCost, Double maxRetailPrice, int orderQuantity, int inStockQuantity, int backOrderQuantity, Date lastStockDate, Date lastSaleDate, int minimumQuantity, int maximumQuantity, int reservedQuantity, int availableQuantity) {
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
        this.availableQuantity = availableQuantity;
    }

    public InventoryDTO(
            String skuId,
            Double unitCost,
            Double maxRetailPrice,
            int orderQuantity,
            int inStockQuantity,
            int backOrderQuantity,
            Date lastStockDate,
            Date lastSaleDate,
            int minimumQuantity,
            int maximumQuantity,
            int reservedQuantity) {
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
        this.availableQuantity = inStockQuantity - reservedQuantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryDTO{");
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
        sb.append(", availableQuantity=").append(availableQuantity);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryDTO that = (InventoryDTO) o;

        if (orderQuantity != that.orderQuantity) return false;
        if (inStockQuantity != that.inStockQuantity) return false;
        if (backOrderQuantity != that.backOrderQuantity) return false;
        if (minimumQuantity != that.minimumQuantity) return false;
        if (maximumQuantity != that.maximumQuantity) return false;
        if (reservedQuantity != that.reservedQuantity) return false;
        if (availableQuantity != that.availableQuantity) return false;
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
        result = 31 * result + availableQuantity;
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

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
