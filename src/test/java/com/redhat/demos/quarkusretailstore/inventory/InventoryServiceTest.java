package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryService;
import com.redhat.demos.quarkusretailstore.products.ProductMasterRepository;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceTest.class);

    @Inject
    InventoryService inventoryService;

    @InjectSpy
    InventoryRepository inventoryRepository;

    @InjectMock
    ProductMasterRepository productMasterRepository;

    @Test
    public void testInventory() {

        Collection<InventoryDTO> completeInventory = inventoryService.getCompeleteInventory();
        Mockito.verify(inventoryRepository, Mockito.times(1)).streamAll();
    }

/*
    @Test @Order(3)
    public void testUpdatingInventory(){

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

        inventoryService.

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
    @Test @Order(1) @Transactional
    public void testMarshallingInventoryFromJson() {

        ProductMasterDTO productMasterDTO = new ProductMasterDTO(UUID.randomUUID().toString(), "Test Product");
        InventoryDTO inventoryDTO = new InventoryDTO(
                productMasterDTO,
                Double.valueOf(19.99),
                Double.valueOf(24.99),
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
        assertEquals(productMasterDTO.getSkuId(), inventory.getProductMaster().getSkuId());
        assertNotNull(inventory);
        assertEquals(Double.valueOf(19.99), inventory.getUnitCost());
        assertEquals(Double.valueOf(24.99), inventory.getMaxRetailPrice());
        assertEquals(1, inventory.getOrderQuantity());
        assertEquals(8, inventory.getInStockQuantity());
        assertEquals(0, inventory.getBackOrderQuantity());
        assertEquals(10, inventory.getMinimumQuantity());
        assertEquals(15, inventory.getMaximumQuantity());
    }

}
