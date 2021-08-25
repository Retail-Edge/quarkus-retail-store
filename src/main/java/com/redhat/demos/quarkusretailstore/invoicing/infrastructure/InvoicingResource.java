package com.redhat.demos.quarkusretailstore.invoicing.infrastructure;

import com.redhat.demos.quarkusretailstore.invoicing.NoSuchInvoiceException;
import com.redhat.demos.quarkusretailstore.invoicing.api.*;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.ui.api.InvoiceJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/invoicing")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvoicingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoicingResource.class);

    @Inject
    InvoiceService invoiceService;

    @POST
    public Response createInvoice(final InvoiceJson invoiceJson) {

        // Marshall an InvoiceDTO from the InvoiceJson
        InvoiceDTO invoiceDTO = new InvoiceDTO(
                invoiceJson.getInvoiceId(),
                new InvoiceHeaderDTO(
                        invoiceJson.getInvoiceHeader().getStoreId(),
                        invoiceJson.getInvoiceHeader().getDate(),
                        invoiceJson.getInvoiceHeader().getTotalDollarAmount(),
                        invoiceJson.getInvoiceHeader().getNumberOfLines()),
                        invoiceJson.getInvoiceLines().stream().map(invoiceLineJson -> {
                            return new InvoiceLineDTO(
                                    new ProductMaster(invoiceLineJson.getProductMaster().getSkuId(), invoiceLineJson.getProductMaster().getDescription()),
                                    invoiceLineJson.getBillQuantity(),
                                    invoiceLineJson.getUnitPrice(),
                                    invoiceLineJson.getExtendedPrice(),
                                    invoiceLineJson.getUnitOfMeasure());
                        }).collect(Collectors.toList()),
                        invoiceJson.getCustomerName());

        InvoiceDTO result = invoiceService.createInvoice(invoiceDTO);

        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @GET
    @Path("/{invoiceId}")
    public Response getInvoiceById(@PathParam("invoiceId") final String invoiceId){

        try {
            InvoiceDTO invoice = invoiceService.findById(invoiceId);
            return Response.status(Response.Status.FOUND).entity(invoice).build();
        } catch (NoSuchInvoiceException e) {
            LOGGER.error(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
