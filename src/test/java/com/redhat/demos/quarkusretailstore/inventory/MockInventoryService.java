package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.Inventory;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryService;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.ui.api.LegacyInventoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Alternative
@ApplicationScoped
public class MockInventoryService implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockInventoryService.class);

    Collection<InventoryDTO> completeInventory = new ArrayList(
            Arrays.asList(
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "A mocked product"),
                            Double.valueOf(18.99),
                            Double.valueOf(37.99),
                            1,
                            10,
                            0,
                            Calendar.getInstance().getTime(),
                            Calendar.getInstance().getTime(),
                            5,
                            10,
                            10).toInventoryDTO(),
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "Another mocked product"),
                            Double.valueOf(19.99),
                            Double.valueOf(39.99),
                            1,
                            11,
                            0,
                            Calendar.getInstance().getTime(),
                            Calendar.getInstance().getTime(),
                            6,
                            11,
                            10),
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "And another mocked product"),
                            Double.valueOf(20.99),
                            Double.valueOf(39.99),
                            3,
                            12,
                            0,
                            Calendar.getInstance().getTime(),
                            Calendar.getInstance().getTime(),
                            7,
                            12,
                            12),
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "A third mocked product"),
                            Double.valueOf(21.99),
                            Double.valueOf(40.99),
                            4,
                            13,
                            0,
                            Calendar.getInstance().getTime(),
                            Calendar.getInstance().getTime(),
                            8,
                            13,
                            13),
                    new Inventory(new ProductMaster(UUID.randomUUID().toString(), "A fourth mocked product"),
                            Double.valueOf(22.99),
                            Double.valueOf(41.99),
                            5,
                            14,
                            0,
                            Calendar.getInstance().getTime(),
                            Calendar.getInstance().getTime(),
                            9,
                            14, 14)
            )
    );

    @Override
    public Collection<LegacyInventoryDTO> legacyGetCompleteInventory() {
        return null;
    }

    @Override
    public Collection<InventoryDTO> getCompeleteInventory() {
        return completeInventory;
    }

    @Override
    public InventoryDTO addInventory(final InventoryDTO inventoryDTO) {

        LOGGER.info("adding inventory for: {}", inventoryDTO);
        return inventoryDTO;
    }

    @Override
    public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
        return inventoryDTO;
    }
}
