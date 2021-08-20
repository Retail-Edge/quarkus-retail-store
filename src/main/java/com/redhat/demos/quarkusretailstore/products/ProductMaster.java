package com.redhat.demos.quarkusretailstore.products;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductMaster extends PanacheEntityBase {

    @Id
    @Column(nullable = false, unique = true, name = "product_id")
    String skuId;

    String description;

    public ProductMaster(final String skuId, final String description) {
        this.skuId = skuId;
        this.description = description;
    }

    public ProductMaster() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductMaster{");
        sb.append("skuId='").append(skuId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductMaster that = (ProductMaster) o;

        if (skuId != null ? !skuId.equals(that.skuId) : that.skuId != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = skuId != null ? skuId.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
