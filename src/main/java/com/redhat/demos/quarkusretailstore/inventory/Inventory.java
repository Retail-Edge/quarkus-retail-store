package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.Map;

@Entity
class Inventory extends PanacheEntity {

    /**
     * The product
     */
    @OneToOne(fetch = FetchType.EAGER)
    ProductMaster productMaster;

    /**
     * Price paid to supplier
     */
    Double unitCost;

    /**
     * Maximum price the customer can be charged for this product
     */
    Double maxRetailPrice;

    /**
     * Quantity of product that are currently outstanding on a purchase order
     */
    int orderQuantity;

    /**
     * Quantity of product currently on site
     */
    int inStockQuantity;

    /**
     * Quantity of product that cannot be fulfilled from the supplier
     */
    int backOrderQuantity;

    /**
     * Last date the product was received
     */
    Date lastStockDate;

    /**
     * Last date an Invoice was created for this product
     *
     * @see com.redhat.demos.quarkusretailstore.invoicing.domain.Invoice
     */
    Date lastSaleDate;

    /**
     * Minimum quantity that should be kept in stock (based on previous sales)
     */
    int minimumQuantity;

    /**
     * Maximum quantity that should be kept in stock (based on previous sales)
     */
    int maximumQuantity;

    /**
     * Number of products on hold for pick up, e-commerce, etc.
     */
    int reservedQuantity;

    /**
     * Derived number: inStockQuantity - reservedQuantity
     * @return
     */
    public int availableQuantity() {
        return inStockQuantity - reservedQuantity;
    }

    public Inventory() {
    }

    public Inventory(ProductMaster productMaster, Double unitCost, Double maxRetailPrice, int orderQuantity, int inStockQuantity, int backOrderQuantity, Date lastStockDate, Date lastSaleDate, int minimumQuantity, int maximumQuantity, int reservedQuantity) {
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
        this.reservedQuantity = reservedQuantity;
    }

    public static Inventory from(final InventoryDTO inventoryDTO, ProductMaster productMaster) {

        return new Inventory(
                productMaster,
                inventoryDTO.getUnitCost(),
                inventoryDTO.getMaxRetailPrice(),
                inventoryDTO.getOrderQuantity(),
                inventoryDTO.getInStockQuantity(),
                inventoryDTO.getBackOrderQuantity(),
                inventoryDTO.getLastStockDate(),
                inventoryDTO.getLastSaleDate(),
                inventoryDTO.getMinimumQuantity(),
                inventoryDTO.getMaximumQuantity(),
                inventoryDTO.getReservedQuantity()
        );
    }

    public InventoryDTO toInventoryDTO() {

        return new InventoryDTO(
                this.productMaster.getSkuId(),
                this.unitCost,
                this.maxRetailPrice,
                this.orderQuantity,
                this.inStockQuantity,
                this.backOrderQuantity,
                this.lastStockDate,
                this.lastSaleDate,
                this.minimumQuantity,
                this.maximumQuantity,
                this.reservedQuantity,
                this.availableQuantity());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Inventory{");
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

        Inventory inventory = (Inventory) o;

        if (orderQuantity != inventory.orderQuantity) return false;
        if (inStockQuantity != inventory.inStockQuantity) return false;
        if (backOrderQuantity != inventory.backOrderQuantity) return false;
        if (minimumQuantity != inventory.minimumQuantity) return false;
        if (maximumQuantity != inventory.maximumQuantity) return false;
        if (productMaster != null ? !productMaster.equals(inventory.productMaster) : inventory.productMaster != null)
            return false;
        if (unitCost != null ? !unitCost.equals(inventory.unitCost) : inventory.unitCost != null) return false;
        if (maxRetailPrice != null ? !maxRetailPrice.equals(inventory.maxRetailPrice) : inventory.maxRetailPrice != null)
            return false;
        if (lastStockDate != null ? !lastStockDate.equals(inventory.lastStockDate) : inventory.lastStockDate != null)
            return false;
        return lastSaleDate != null ? lastSaleDate.equals(inventory.lastSaleDate) : inventory.lastSaleDate == null;
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

    public ProductMaster getProductMaster() {
        return productMaster;
    }

    public void setProductMaster(ProductMaster productMaster) {
        this.productMaster = productMaster;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public void setMaxRetailPrice(Double maxRetailPrice) {
        this.maxRetailPrice = maxRetailPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getInStockQuantity() {
        return inStockQuantity;
    }

    public void setInStockQuantity(int inStockQuantity) {
        this.inStockQuantity = inStockQuantity;
    }

    public int getBackOrderQuantity() {
        return backOrderQuantity;
    }

    public void setBackOrderQuantity(int backOrderQuantity) {
        this.backOrderQuantity = backOrderQuantity;
    }

    public Date getLastStockDate() {
        return lastStockDate;
    }

    public void setLastStockDate(Date lastStockDate) {
        this.lastStockDate = lastStockDate;
    }

    public Date getLastSaleDate() {
        return lastSaleDate;
    }

    public void setLastSaleDate(Date lastSaleDate) {
        this.lastSaleDate = lastSaleDate;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public int getMaximumQuantity() {
        return maximumQuantity;
    }

    public void setMaximumQuantity(int maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }

}
