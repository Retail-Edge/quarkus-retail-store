package com.redhat.demos.quarkusretailstore.ui.infrastructure;

import com.redhat.demos.quarkusretailstore.invoicing.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.NoSuchProductException;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.infrastructure.ProductsService;
import com.redhat.demos.quarkusretailstore.ui.api.ProductMasterJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    @Inject
    ProductsService productsService;

    @GET
    public Response getProducts() {

        Collection<ProductMaster> allProducts = productsService.getAllProducts();
        LOGGER.debug("Returning {} products", allProducts.size());
        return Response.status(200).entity(allProducts).build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") String skuId) {

        ProductMaster productMaster = null;
        try {
            productMaster = productsService.getProductBySkuId(skuId);
            LOGGER.debug("Returning {}", productMaster);
            return Response.status(Response.Status.OK).entity(new ProductMasterJson(productMaster.getSkuId(), productMaster.getDescription())).build();
        } catch (NoSuchProductException e) {
            LOGGER.debug("No ProductMaster found for: {}", skuId);
            LOGGER.debug(e.getMessage());
            return Response.status(Response.Status.OK).entity(null).build();
        }
    }

    @POST@Transactional
    public Response addProduct(final ProductMasterJson productMasterJson) {

        ProductMasterDTO productMasterDTO = new ProductMasterDTO(productMasterJson.getDescription());
        ProductMasterDTO result = productsService.addProduct(productMasterDTO);
        return Response.status(Response.Status.CREATED).entity(new ProductMasterJson(result.getSkuId(), result.getDescription())).build();
    }
}
