package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.ProductMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Alternative;

@Alternative
public class MockProductMasterRepository extends ProductMasterRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockProductMasterRepository.class);

    @Override
    public ProductMaster findBySkuId(String skuId){
        LOGGER.info("mocking findBySkuId");
        return InventoryTestUtil.mockProductMaster();
    }
}
