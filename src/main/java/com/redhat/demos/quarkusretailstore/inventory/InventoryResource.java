package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.inventory.api.InventoryDTO;
import com.redhat.demos.quarkusretailstore.inventory.api.InventoryService;
import com.redhat.demos.quarkusretailstore.inventory.api.NoSuchInventoryRecordException;
import com.redhat.demos.quarkusretailstore.ui.InventoryJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

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
    public Response createInventory(final InventoryJson inventoryJson) {

        LOGGER.debug("creating inventory from: {}", inventoryJson);


        InventoryDTO inventoryDTO = new InventoryDTO(
                inventoryJson.productMaster,
                inventoryJson.unitCost,
                inventoryJson.maxRetailPrice,
                inventoryJson.orderQuantity,
                inventoryJson.inStockQuantity,
                inventoryJson.backOrderQuantity,
                LocalDateTime.ofInstant(inventoryJson.lastStockDate.toInstant(), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(inventoryJson.lastSaleDate.toInstant(), ZoneId.systemDefault()),
                inventoryJson.minimumQuantity,
                inventoryJson.maximumQuantity
        );
        InventoryDTO result = inventoryService.addInventory(inventoryDTO);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    public Response updateInventory(final InventoryJson inventoryJson) {

        LOGGER.debug("updating inventory from: {}", inventoryJson);
        try {
            InventoryDTO inventoryDTO = new InventoryDTO(
                    inventoryJson.productMaster,
                    inventoryJson.unitCost,
                    inventoryJson.maxRetailPrice,
                    inventoryJson.orderQuantity,
                    inventoryJson.inStockQuantity,
                    inventoryJson.backOrderQuantity,
                    LocalDateTime.ofInstant(inventoryJson.lastStockDate.toInstant(), ZoneId.systemDefault()),
                    LocalDateTime.ofInstant(inventoryJson.lastSaleDate.toInstant(), ZoneId.systemDefault()),
                    inventoryJson.minimumQuantity,
                    inventoryJson.maximumQuantity
            );
            InventoryDTO result = inventoryService.updateInventory(inventoryDTO);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (NoSuchInventoryRecordException e) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }
    }
}
