package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;

import java.util.*;

/**
 * Domain object representing Invoices.  This class is essentially a wrapper for the InvoiceHeader and InvoiceLines, the combination of which make up an Invoice.
 *
 * @see InvoiceHeader
 * @see InvoiceLine
 */
public class Invoice {

    /**
     * Brick and mortar location identifier
     */
    String storeId;

    /**
     * The actual invoice
     */
    InvoiceRecord invoiceRecord;

    public static Invoice from(InvoiceRecord invoiceRecord) {
        return new Invoice(invoiceRecord);
    }

    public static InvoiceEventResult create(final InvoiceDTO invoiceDTO, final Map<String, ProductMaster> products) {

        Invoice invoice = new Invoice(invoiceDTO, products);
        return new InvoiceEventResult(invoice, Arrays.asList(InvoiceEvent.from(invoice.invoiceRecord)));
    }

    public Invoice(final InvoiceDTO invoiceDTO, final Map<String, ProductMaster> products) {

        // create an InvoiceHeader from the DTO
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        invoiceHeader.setDate(invoiceDTO.getInvoiceHeader().getDate());
        invoiceHeader.setStoreId(storeId);
        invoiceHeader.setNumberOfLines(invoiceDTO.getInvoiceHeader().getNumberOfLines());
        invoiceHeader.setTotalDollarAmount(invoiceDTO.getInvoiceHeader().getTotalDollarAmount());

        // create a Collection of InvoiceLines from the DTO
        Collection<InvoiceLine> invoiceLines = new ArrayList<>(invoiceDTO.getInvoiceLines().size());
        invoiceDTO.getInvoiceLines().forEach(invoiceLineDTO -> {
            InvoiceLine invoiceLine = new InvoiceLine(
                    products.get(invoiceLineDTO.getSkuId()),
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
