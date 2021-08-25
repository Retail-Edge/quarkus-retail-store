package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.inventory.api.NoSuchInventoryRecordException;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryService;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.ProductMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Inject
    InventoryRepository inventoryRepository;

    @Inject
    ProductMasterRepository productMasterRepository;

    @Override
    public Collection<InventoryDTO> getCompeleteInventory() {

        return inventoryRepository.streamAll().map(inventory -> {
            return inventory.toInventoryDTO();
        }).collect(Collectors.toList());
    }

    @Override @Transactional
    public InventoryDTO addInventory(final InventoryDTO inventoryDTO) {

        LOGGER.debug("adding: {}", inventoryDTO);
        ProductMaster productMaster = ProductMaster.findBySkuId(inventoryDTO.getProductMaster().getSkuId());
        Inventory inventory = new Inventory(
                productMaster,
                inventoryDTO.getUnitCost(),
                inventoryDTO.getMaxRetailPrice(),
                inventoryDTO.getOrderQuantity(),
                inventoryDTO.getInStockQuantity(),
                inventoryDTO.getBackOrderQuantity(),
                inventoryDTO.getLastStockDate(),
                inventoryDTO.getLastSaleDate(),
                inventoryDTO.getMinimumQuantity(),
                inventoryDTO.getMaximumQuantity()
        );
        inventoryRepository.persist(inventory);
        return inventory.toInventoryDTO();
    }

    @Override @Transactional
    public InventoryDTO updateInventory(final InventoryDTO inventoryDTO) throws NoSuchInventoryRecordException {
        LOGGER.debug("updating: {}", inventoryDTO);
        Inventory inventory = inventoryRepository.findById(inventoryDTO.getProductMaster().getSkuId());
        if (inventory == null) {
            throw new NoSuchInventoryRecordException(inventoryDTO.getProductMaster().getSkuId());
        }
        inventory.setBackOrderQuantity(inventoryDTO.getBackOrderQuantity());
        inventory.setInStockQuantity(inventoryDTO.getInStockQuantity());
        inventory.setLastSaleDate(inventoryDTO.getLastSaleDate());
        inventory.setLastStockDate(inventoryDTO.getLastStockDate());
        inventory.setMaximumQuantity(inventoryDTO.getMaximumQuantity());
        inventory.setMinimumQuantity(inventoryDTO.getMinimumQuantity());
        inventory.setOrderQuantity(inventoryDTO.getOrderQuantity());
        inventory.setMaxRetailPrice(inventoryDTO.getMaxRetailPrice());
        inventory.setUnitCost(inventoryDTO.getUnitCost());
        inventoryRepository.persist(inventory);
        return inventory.toInventoryDTO();
    }
}
