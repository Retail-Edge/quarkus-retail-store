package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.Inventory;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryService;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Alternative
@ApplicationScoped
public class MockInventoryService implements InventoryService {

    Collection<InventoryDTO> completeInventory = new ArrayList(
            Arrays.asList(
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "A mocked product"),
                            18.99,
                            37.99,
                            1,
                            10,
                            0,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            5,
                            10).toInventoryDTO(),
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "Another mocked product"),
                            19.99,
                            39.99,
                            1,
                            11,
                            0,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            6,
                            11),
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "And another mocked product"),
                            20.99,
                            39.99,
                            3,
                            12,
                            0,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            7,
                            12),
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "A third mocked product"),
                            21.99,
                            40.99,
                            4,
                            13,
                            0,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            8,
                            13),
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "A fourth mocked product"),
                            22.99,
                            41.99,
                            5,
                            14,
                            0,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            9,
                            14)
            )
    );

    @Override
    public Collection<InventoryDTO> getCompeleteInventory() {
        return completeInventory;
    }

    @Override
    public InventoryDTO addInventory(InventoryDTO inventoryJson) {
        return null;
    }

    @Override
    public InventoryDTO updateInventory(InventoryDTO inventoryJson) {
        return null;
    }
}
