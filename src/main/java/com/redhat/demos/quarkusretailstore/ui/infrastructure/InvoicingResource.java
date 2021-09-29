package com.redhat.demos.quarkusretailstore.ui.infrastructure;

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
import java.util.stream.Collectors;

@Path("/invoicing")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvoicingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoicingResource.class);

    @Inject
    InvoiceService invoiceService;

    /**
     * {
     *     "invoiceId": "60cfbaaf-530e-437e-820d-d06e21979731",
     *     "invoiceHeader": {
     *         "id": "4a73bae0-1a19-4aed-8a8d-c46be275830f",
     *         "storeId": "ATLANTA-01",
     *         "date": 1629823359724,
     *         "totalDollarAmount": 35.99,
     *         "numberOfLines": 2
     *     },
     *     "invoiceLines": [
     *         {
     *             "skuId": "f17038b9-7833-4976-8782-9c0dc2d180b4",
     *             "billQuantity": 1,
     *             "unitPrice": 21.99
     *         }
     *     ],
     *     "customerName": "Eeyore"
     * }
     * @param invoiceJson
     * @return
     */
    @POST
    public Response createInvoice(final InvoiceJson invoiceJson) {

        LOGGER.debug("creating invoice from: {}", invoiceJson);
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
                                    invoiceLineJson.getSkuId(),
                                    invoiceLineJson.getBillQuantity(),
                                    invoiceLineJson.getUnitPrice(),
                                    invoiceLineJson.getUnitPrice() * invoiceLineJson.getBillQuantity(),
                                    invoiceLineJson.getUnitOfMeasure());
                        }).collect(Collectors.toList()),
                        invoiceJson.getCustomerName());

        LOGGER.debug("Sending InvoiceDTO: {}", invoiceDTO);
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
