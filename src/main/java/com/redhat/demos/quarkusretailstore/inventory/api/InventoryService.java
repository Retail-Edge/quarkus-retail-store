package com.redhat.demos.quarkusretailstore.inventory.api;

import java.util.Collection;

public interface InventoryService {

    Collection<InventoryDTO> getCompeleteInventory();

    InventoryDTO addInventory(final InventoryDTO inventoryDTO);

    InventoryDTO updateInventory(final InventoryDTO inventoryDTO) throws NoSuchInventoryRecordException;
}
