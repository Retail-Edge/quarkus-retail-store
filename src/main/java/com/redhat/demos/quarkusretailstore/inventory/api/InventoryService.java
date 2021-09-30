package com.redhat.demos.quarkusretailstore.inventory.api;

import com.redhat.demos.quarkusretailstore.ui.api.LegacyInventoryDTO;

import java.util.Collection;

public interface InventoryService {

    Collection<LegacyInventoryDTO> legacyGetCompleteInventory();

    Collection<InventoryDTO> getCompeleteInventory();

    InventoryDTO addInventory(final InventoryDTO inventoryDTO);

    InventoryDTO updateInventory(final InventoryDTO inventoryDTO) throws NoSuchInventoryRecordException;
}
