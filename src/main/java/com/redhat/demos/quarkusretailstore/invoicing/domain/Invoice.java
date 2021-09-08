package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.redhat.demos.quarkusretailstore.invoicing.api.InvoiceDTO;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Domain object representing Invoices
 */
public class Invoice {

    String storeId;

    InvoiceRecord invoiceRecord;

    public static Invoice from(InvoiceRecord invoiceRecord) {
        return new Invoice(invoiceRecord);
    }

    public static InvoiceEventResult create(final InvoiceDTO invoiceDTO) {

        Invoice invoice = new Invoice(invoiceDTO);
        return new InvoiceEventResult(invoice, Arrays.asList(InvoiceEvent.from(invoice.invoiceRecord)));
    }

    public Invoice(InvoiceDTO invoiceDTO) {

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
                    new ProductMaster(invoiceLineDTO.getProductMaster().getSkuId(), invoiceLineDTO.getProductMaster().getDescription()),
                    invoiceLineDTO.getBillQuantity(),
                    invoiceLineDTO.getUnitPrice(),
                    invoiceLineDTO.getExtendedPrice(),
                    invoiceLineDTO.getUnitOfMeasure()
            );

            invoiceLine.setBillQuantity(invoiceLineDTO.getBillQuantity());
            invoiceLine.setExtendedPrice(invoiceLineDTO.getExtendedPrice());
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Invoice{");
        sb.append("invoiceRecord=").append(invoiceRecord);
        sb.append('}');
        return sb.toString();
    }
}
