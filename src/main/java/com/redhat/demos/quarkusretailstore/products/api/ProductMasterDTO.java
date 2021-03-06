package com.redhat.demos.quarkusretailstore.products.api;

public class ProductMasterDTO {

    final String skuId;

    final String description;

    public ProductMasterDTO(String skuId, String description) {
        this.skuId = skuId;
        this.description = description;
    }

    public ProductMasterDTO(String description) {
        skuId = null;
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductMasterDTO{");
        sb.append("skuId='").append(skuId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductMasterDTO that = (ProductMasterDTO) o;

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

    public String getDescription() {
        return description;
    }
}
