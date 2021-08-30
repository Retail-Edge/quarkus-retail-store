package com.redhat.demos.quarkusretailstore.products;

import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.api.ProductsService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@Alternative
@ApplicationScoped
public class MockProductsService implements ProductsService {

    public Collection<ProductMasterDTO> getAllProducts() {
        return new ArrayList(
            Arrays.asList(new ProductMaster(UUID.randomUUID().toString(), "A product"),
            new ProductMaster(UUID.randomUUID().toString(), "Another product"),
            new ProductMaster(UUID.randomUUID().toString(), "Yet another product"))
        );
    }

    @Override
    public ProductMasterDTO getProductBySkuId(String skuId) throws NoSuchProductException {
        return new ProductMasterDTO(skuId, "A mocked product");
    }

    @Override
    public ProductMasterDTO addProduct(ProductMasterDTO productMasterDTO) {

        return new ProductMasterDTO(UUID.randomUUID().toString(), productMasterDTO.getDescription());
    }
}
