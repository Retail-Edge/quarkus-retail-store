package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Alternative;

@Alternative
public class MockInventoryRepository extends InventoryRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(MockInventoryRepository.class);

    public InventoryDTO persist(InventoryDTO inventoryDTO) {

        LOGGER.debug("persist called for: {}", inventoryDTO);
        return inventoryDTO;
    }

    @Override
    public long count() {
        return 1L;
    }
}
