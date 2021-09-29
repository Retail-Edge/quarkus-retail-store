package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Domain object representing Invoices.  This class is essentially a wrapper for the InvoiceHeader and InvoiceLines, the combination of which make up an Invoice.
 *
 * @see InvoiceHeader
 * @see InvoiceLine
 */
public class Invoice {

    private static final Logger LOGGER = LoggerFactory.getLogger(Invoice.class);

    /**
     * The actual invoice
     */
    InvoiceRecord invoiceRecord;

    public static Invoice from(InvoiceRecord invoiceRecord) {
        return new Invoice(invoiceRecord);
    }

    public static InvoiceEventResult create(final InvoiceDTO invoiceDTO, final Map<String, ProductMaster> products) {

        Invoice invoice = new Invoice(invoiceDTO, products);
        LOGGER.debug("Invoice created: {}", invoice);
        return new InvoiceEventResult(invoice, Arrays.asList(InvoiceEvent.from(invoice.invoiceRecord)));
    }

    public Invoice(final InvoiceDTO invoiceDTO, final Map<String, ProductMaster> products) {

        LOGGER.debug("Creating an Invoice from: {}", invoiceDTO);

        // create an InvoiceHeader from the DTO
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        invoiceHeader.setDate(invoiceDTO.getInvoiceHeader().getDate());
        invoiceHeader.setStoreId(invoiceDTO.getInvoiceHeader().getStoreId());
        invoiceHeader.setNumberOfLines(invoiceDTO.getInvoiceHeader().getNumberOfLines());
        invoiceHeader.setTotalDollarAmount(invoiceDTO.getInvoiceHeader().getTotalDollarAmount());

        // create a Collection of InvoiceLines from the DTO
        Collection<InvoiceLine> invoiceLines = new ArrayList<>(invoiceDTO.getInvoiceLines().size());
        invoiceDTO.getInvoiceLines().forEach(invoiceLineDTO -> {
            ProductMaster productMaster = products.get(invoiceLineDTO.getSkuId());
            InvoiceLine invoiceLine = new InvoiceLine(
                    productMaster,
                    invoiceLineDTO.getBillQuantity(),
                    invoiceLineDTO.getUnitPrice(),
                    invoiceLineDTO.getUnitOfMeasure()
            );

            invoiceLine.setBillQuantity(invoiceLineDTO.getBillQuantity());
            invoiceLines.add(invoiceLine);
        });

        // Create the InvoiceRecord
        invoiceRecord = new InvoiceRecord(
                invoiceDTO.getInvoiceId(),
                invoiceHeader,
                invoiceLines,
                invoiceDTO.getCustomerName());
    }

    private Invoice(final InvoiceRecord record) {
        invoiceRecord = record;
    }

    public void update(final InvoiceDTO invoiceData) {

        invoiceRecord.customerName = invoiceData.getCustomerName();
    }

    public String getId() {
        return invoiceRecord.invoiceId;
    }

    public String getCustomer(){
        return invoiceRecord.customerName;
    }

    public InvoiceHeader getInvoiceHeader(){ return invoiceRecord.invoiceHeader; }

    public Collection<InvoiceLine> getInvoiceLines() {
        return invoiceRecord.getInvoiceLines();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Invoice{");
        sb.append("invoiceRecord=").append(invoiceRecord);
        sb.append('}');
        return sb.toString();
    }
}
