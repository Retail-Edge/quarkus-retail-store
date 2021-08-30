package com.redhat.demos.quarkusretailstore.products.api;

import com.redhat.demos.quarkusretailstore.products.NoSuchProductException;

import java.util.Collection;

public interface ProductsService {

    public Collection<ProductMasterDTO> getAllProducts();

    public ProductMasterDTO getProductBySkuId(String skuId) throws NoSuchProductException;

    public ProductMasterDTO addProduct(final ProductMasterDTO productMasterDTO);
}
