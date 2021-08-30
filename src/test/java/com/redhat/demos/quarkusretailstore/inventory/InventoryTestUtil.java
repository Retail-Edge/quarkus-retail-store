package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;

import java.util.UUID;

public class InventoryTestUtil {

    static String skuId = UUID.randomUUID().toString();

    static String description = "A Mock Product";

    static ProductMaster mockProductMaster() {

        return new ProductMaster(skuId, description);
    }

    public static ProductMasterDTO mockProductMasterDTO() {

        return new ProductMasterDTO(skuId, description);
    }
}
