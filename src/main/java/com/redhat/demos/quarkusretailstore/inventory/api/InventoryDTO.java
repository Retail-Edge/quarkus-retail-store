package com.redhat.demos.quarkusretailstore.inventory.api;

import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InventoryDTO {

    final ProductMasterDTO productMaster;

    final BigDecimal unitCost;

    final BigDecimal maxRetailPrice;

    final int orderQuantity;

    final int inStockQuantity;

    final int backOrderQuantity;

    final LocalDateTime lastStockDate;

    final LocalDateTime lastSaleDate;

    final int minimumQuantity;

    final int maximumQuantity;

    public InventoryDTO(ProductMasterDTO productMaster, BigDecimal unitCost, BigDecimal maxRetailPrice, int orderQuantity, int inStockQuantity, int backOrderQuantity, LocalDateTime lastStockDate, LocalDateTime lastSaleDate, int minimumQuantity, int maximumQuantity) {
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
        final StringBuilder sb = new StringBuilder("InventoryDTO{");
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

        InventoryDTO that = (InventoryDTO) o;

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

    public LocalDateTime getLastStockDate() {
        return lastStockDate;
    }

    public LocalDateTime getLastSaleDate() {
        return lastSaleDate;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public int getMaximumQuantity() {
        return maximumQuantity;
    }
}
