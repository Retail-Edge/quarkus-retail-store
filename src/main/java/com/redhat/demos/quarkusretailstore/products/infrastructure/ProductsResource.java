package com.redhat.demos.quarkusretailstore.products.infrastructure;

import com.redhat.demos.quarkusretailstore.invoicing.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.NoSuchProductException;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.ProductMasterRepository;
import com.redhat.demos.quarkusretailstore.ui.api.ProductMasterJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    @Inject
    ProductMasterRepository productMasterRepository;

    @GET
    public Response getProducts() {

        Collection<ProductMaster> allProducts = productMasterRepository.listAll();
        LOGGER.debug("Returning {} products", allProducts.size());
        return Response.status(200).entity(allProducts).build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") String skuId) {

        ProductMaster productMaster = productMasterRepository.find("skuId", skuId).singleResult();
        LOGGER.debug("Returning {}", productMaster);
        return Response.status(200).entity(productMaster).build();
    }

    @POST@Transactional
    public Response addProduct(final ProductMasterJson productMasterJson) {

        ProductMaster productMasterDTO = new ProductMaster(productMasterJson.getSkuId(), productMasterJson.getDescription());
        productMasterRepository.persist(productMasterDTO);
        return Response.status(Response.Status.CREATED).entity(productMasterJson).build();
    }
}
