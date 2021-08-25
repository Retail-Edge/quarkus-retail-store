package com.redhat.demos.quarkusretailstore.products;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductMasterRepository implements PanacheRepository<ProductMaster> {

    public ProductMaster findById(final String id) {
        return ProductMaster.findById(id);
    }

    public ProductMaster findBySkuId(final String skuId){

        return ProductMaster.findBySkuId(skuId);
    }
}
