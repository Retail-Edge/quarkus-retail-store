package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.inventory.api.NoSuchInventoryRecordException;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryService;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.ProductMasterRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceTest.class);

    @Inject
    InventoryService inventoryService;

    @Inject
    InventoryRepository inventoryRepository;

    @Inject
    ProductMasterRepository productMasterRepository;

    @Test @Order(4)
    public void testInventory() {

        Collection<InventoryDTO> completeInventory = inventoryService.getCompeleteInventory();
        assertNotNull(completeInventory);
        assertEquals(1, completeInventory.size());
    }

/*
    @Test @Order(3)
    public void testUpdatingInventory(){

        List<Inventory> completeInventory = inventoryRepository.listAll();
        List<ProductMaster> completeProducts = productMasterRepository.listAll();
        assertNotNull(completeInventory);
        assertTrue(completeInventory.size() >= 1);
        Inventory inventory = completeInventory.get(0);
        LOGGER.debug("inventory: {}", inventory);
        int expectedValue = inventory.getOrderQuantity() + 1;

        InventoryDTO inventoryDTO = new InventoryDTO(
                inventory.getProductMaster(),
                inventory.getUnitCost(),
                inventory.getMaxRetailPrice(),
                inventory.getOrderQuantity() + 1,
                inventory.getInStockQuantity(),
                inventory.getBackOrderQuantity(),
                inventory.getLastStockDate(),
                inventory.getLastSaleDate(),
                inventory.getMinimumQuantity(),
                inventory.getMaximumQuantity()
        );

        try {
            inventoryService.updateInventory(inventoryDTO);
        } catch (NoSuchInventoryRecordException e) {
            assertNull(e);
        }

        Inventory updatedInventory = inventoryRepository.findById(inventory.id);
        assertEquals(expectedValue, updatedInventory.getOrderQuantity());

    }
*/

    @Test
    @Order(2) @Transactional
    public void testAddingInventory() {

        assertTrue(productMasterRepository.count() >= 1);
        ProductMaster prod = productMasterRepository.find("description", "A Product").firstResult();
        InventoryDTO inventoryDTO = new InventoryDTO(
                prod,
                19.99,
                24.99,
                1,
                8,
                0,
                LocalDateTime.of(2021, 8, 15, 4, 30),
                LocalDateTime.now().minusMinutes(3),
                10,
                15
        );

        inventoryService.addInventory(inventoryDTO);

        long count = inventoryRepository.count();
        assertEquals(1, count);
    }

    @Test @Order(1) @Transactional
    public void testMarshallingInventoryFromJson() {

        // add a product into the db
        ProductMaster productMaster = new ProductMaster( "A Product");
        productMasterRepository.persist(productMaster);

        InventoryDTO inventoryDTO = new InventoryDTO(
                productMaster,
                19.99,
                24.99,
                1,
                8,
                0,
                LocalDateTime.of(2021, 8, 15, 4, 30),
                LocalDateTime.now().minusMinutes(3),
                10,
                15
        );

        Inventory inventory = Inventory.from(inventoryDTO);
        LOGGER.debug("inventory: {}", inventory);
        assertEquals(productMaster.getSkuId(), inventory.getProductMaster().getSkuId());
        assertNotNull(inventory);
        assertEquals(19.99, inventory.getUnitCost());
        assertEquals(24.99, inventory.getMaxRetailPrice());
        assertEquals(1, inventory.getOrderQuantity());
        assertEquals(8, inventory.getInStockQuantity());
        assertEquals(0, inventory.getBackOrderQuantity());
        assertEquals(10, inventory.getMinimumQuantity());
        assertEquals(15, inventory.getMaximumQuantity());
    }

}
