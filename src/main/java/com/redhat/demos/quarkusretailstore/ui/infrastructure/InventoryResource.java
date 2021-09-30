package com.redhat.demos.quarkusretailstore.ui.infrastructure;

import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryService;
import com.redhat.demos.quarkusretailstore.inventory.api.NoSuchInventoryRecordException;
import com.redhat.demos.quarkusretailstore.ui.api.InventoryJson;
import com.redhat.demos.quarkusretailstore.ui.api.LegacyInventoryJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

@Path("/inventory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryResource.class);

    @Inject
    InventoryService inventoryService;

    @GET
    public Response getCompleteInventory() {

        Collection<InventoryDTO> completeInventory = inventoryService.getCompeleteInventory();
        LOGGER.debug("Returning {} items", completeInventory.size());
        return Response.status(Response.Status.OK).entity(inventoryService.getCompeleteInventory()).build();
    }

    @POST
    public Response legacyCreateInventory(final LegacyInventoryJson legacyInventoryJson) {

        LOGGER.debug("creating inventory from: {}", legacyInventoryJson);

        InventoryDTO inventoryDTO = new InventoryDTO(
                legacyInventoryJson.getProductMasterJson().getSkuId(),
                legacyInventoryJson.getUnitCost(),
                legacyInventoryJson.maxRetailPrice,
                legacyInventoryJson.orderQuantity,
                legacyInventoryJson.inStockQuantity,
                legacyInventoryJson.backOrderQuantity,
                legacyInventoryJson.lastStockDate,
                legacyInventoryJson.lastSaleDate,
                legacyInventoryJson.minimumQuantity,
                legacyInventoryJson.maximumQuantity,
                legacyInventoryJson.getReservedQuantity()
        );

        InventoryDTO result = inventoryService.addInventory(inventoryDTO);

        return Response.status(Response.Status.CREATED).entity(new LegacyInventoryJson(
                legacyInventoryJson.getProductMasterJson(),
                result.getUnitCost(),
                result.getMaxRetailPrice(),
                result.getOrderQuantity(),
                result.getInStockQuantity(),
                result.getBackOrderQuantity(),
                result.getLastStockDate(),
                result.getLastSaleDate(),
                result.getMinimumQuantity(),
                result.getMaximumQuantity(),
                result.getReservedQuantity())).build();
    }

    @POST
    @Path("/v2")
    public Response createInventory(final InventoryJson inventoryJson) {

        LOGGER.debug("creating inventory from: {}", inventoryJson);

        InventoryDTO inventoryDTO = new InventoryDTO(
                inventoryJson.getSkuId(),
                inventoryJson.getUnitCost(),
                inventoryJson.maxRetailPrice,
                inventoryJson.orderQuantity,
                inventoryJson.inStockQuantity,
                inventoryJson.backOrderQuantity,
                inventoryJson.lastStockDate,
                inventoryJson.lastSaleDate,
                inventoryJson.minimumQuantity,
                inventoryJson.maximumQuantity,
                inventoryJson.getReservedQuantity()
        );

        InventoryDTO result = inventoryService.addInventory(inventoryDTO);

        return Response.status(Response.Status.CREATED).entity(new InventoryJson(
                result.getSkuId(),
                result.getUnitCost(),
                result.getMaxRetailPrice(),
                result.getOrderQuantity(),
                result.getInStockQuantity(),
                result.getBackOrderQuantity(),
                result.getLastStockDate(),
                result.getLastSaleDate(),
                result.getMinimumQuantity(),
                result.getMaximumQuantity(),
                result.getReservedQuantity())).build();
    }

    @PUT
    public Response updateInventory(final InventoryJson inventoryJson) {

        LOGGER.debug("updating inventory from: {}", inventoryJson);

        try {
            InventoryDTO inventoryDTO = new InventoryDTO(
                    inventoryJson.getSkuId(),
                    inventoryJson.getUnitCost(),
                    inventoryJson.getMaxRetailPrice(),
                    inventoryJson.getOrderQuantity(),
                    inventoryJson.getInStockQuantity(),
                    inventoryJson.getBackOrderQuantity(),
                    inventoryJson.getLastStockDate(),
                    inventoryJson.getLastSaleDate(),
                    inventoryJson.getMinimumQuantity(),
                    inventoryJson.getMaximumQuantity(),
                    inventoryJson.getReservedQuantity());

            InventoryDTO result = inventoryService.updateInventory(inventoryDTO);

            return Response.status(Response.Status.OK).entity(new InventoryJson(
                    inventoryDTO.getSkuId(),
                    inventoryDTO.getUnitCost(),
                    inventoryDTO.getMaxRetailPrice(),
                    inventoryDTO.getOrderQuantity(),
                    inventoryDTO.getInStockQuantity(),
                    inventoryDTO.getBackOrderQuantity(),
                    inventoryDTO.getLastStockDate(),
                    inventoryDTO.getLastSaleDate(),
                    inventoryDTO.getMinimumQuantity(),
                    inventoryDTO.getMaximumQuantity(),
                    inventoryDTO.getReservedQuantity())).build();

        } catch (NoSuchInventoryRecordException e) {
            return Response.status(Response.Status.OK).entity(null).build();
        }
    }
}
