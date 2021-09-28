package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryService;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.ProductMasterRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class InventoryServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceTest.class);

/*
    @Inject
    InventoryService inventoryService;

    @Inject
    InventoryRepository inventoryRepository;

    @Inject
    ProductMasterRepository productMasterRepository;
*/

    @Test
    public void test() {
        assertTrue(false);
    }

/*
    @Test @Order(4)
    public void testInventory() {

        Collection<InventoryDTO> completeInventory = inventoryService.getCompeleteInventory();
        assertNotNull(completeInventory);
        assertEquals(1, completeInventory.size());
    }

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

    /*
        @Test
        public void testAddingInventory() {

            productMasterRepository.persist(InventoryTestUtil.mockProductMaster());
            productMasterRepository.flush();

            InventoryDTO inventoryDTO = new InventoryDTO(
                    InventoryTestUtil.mockProductMasterDTO(),
                    BigDecimal.valueOf(19.99),
                    BigDecimal.valueOf(24.99),
                    1,
                    8,
                    0,
                    LocalDateTime.of(2021, 8, 15, 4, 30),
                    LocalDateTime.now().minusMinutes(3),
                    10,
                    15
            );

            inventoryService.addInventory(inventoryDTO);
            ArgumentCaptor<Inventory> inventoryCaptor = ArgumentCaptor.forClass(Inventory.class);
            Mockito.verify(inventoryRepository).persist(inventoryCaptor.capture());
        }

    */
/*
    @Test @Transactional
    public void testMarshallingInventoryFromJson() {

        ProductMaster product = new ProductMaster( "A Product");
        productMasterRepository.persist(product);
        productMasterRepository.flush();

        // get a product
        ProductMaster productMaster = productMasterRepository.listAll().get(0);

            InventoryDTO inventoryDTO = new InventoryDTO(
                productMaster.getSkuId(),
                Double.valueOf(19.99),
                Double.valueOf(24.99),
                1,
                8,
                0,
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime(),
                10,
                12,
                12,
                    0
        );

        Inventory inventory = Inventory.from(inventoryDTO, productMaster);
        LOGGER.debug("inventory: {}", inventory);
        assertEquals(productMaster.getSkuId(), inventory.getProductMaster().getSkuId());
        assertNotNull(inventory);
        assertEquals(Double.valueOf(19.99), inventory.getUnitCost());
        assertEquals(Double.valueOf(24.99), inventory.getMaxRetailPrice());
        assertEquals(1, inventory.getOrderQuantity());
        assertEquals(8, inventory.getInStockQuantity());
        assertEquals(0, inventory.getBackOrderQuantity());
        assertEquals(10, inventory.getMinimumQuantity());
        assertEquals(15, inventory.getMaximumQuantity());
    }

*/
}
