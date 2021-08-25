package com.redhat.demos.quarkusretailstore.products.infrastructure;

import com.redhat.demos.quarkusretailstore.invoicing.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.NoSuchProductException;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;

import java.util.Collection;

public interface ProductsService {

    public Collection<ProductMaster> getAllProducts();

    public ProductMaster getProductBySkuId(String skuId) throws NoSuchProductException;

    public ProductMasterDTO addProduct(final ProductMasterDTO productMasterDTO);
}
