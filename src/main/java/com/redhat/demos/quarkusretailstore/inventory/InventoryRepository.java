package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.Inventory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class InventoryRepository implements PanacheRepository<Inventory> {

    protected Inventory findById(final String skuId) {
        return Inventory.find("productMaster.skuId", skuId).firstResult();
    }

}
