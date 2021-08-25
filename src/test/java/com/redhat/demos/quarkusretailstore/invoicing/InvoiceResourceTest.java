package com.redhat.demos.quarkusretailstore.invoicing;

import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.ui.InventoryJson;
import com.redhat.demos.quarkusretailstore.ui.api.InvoiceHeaderJson;
import com.redhat.demos.quarkusretailstore.ui.api.InvoiceJson;
import com.redhat.demos.quarkusretailstore.ui.api.InvoiceLineJson;
import com.redhat.demos.quarkusretailstore.ui.api.ProductMasterJson;
import com.redhat.demos.quarkusretailstore.utils.JsonUtil;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static io.restassured.RestAssured.with;

@QuarkusTest
public class InvoiceResourceTest {

    @Test
    public void testCreateInvoice() {

        InvoiceHeaderJson invoiceHeaderJson = new InvoiceHeaderJson(UUID.randomUUID().toString(), Date.from(Instant.now()), 35.99, 2);
        Collection<InvoiceLineJson> invoiceLineJsonCollection = new ArrayList(
                Arrays.asList(new InvoiceLineJson(new ProductMasterJson(UUID.randomUUID().toString(), "A product description"), BigDecimal.valueOf(1), 21.99, BigDecimal.valueOf(25.99), UnitOfMeasure.EACH))
        );
        InvoiceJson invoiceJson = new InvoiceJson(UUID.randomUUID().toString(), invoiceHeaderJson, invoiceLineJsonCollection, "Eeyore");

        System.out.println(JsonUtil.toJson(invoiceJson));
        with().body(invoiceJson)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/invoicing")
                .then()
                .statusCode(201);

    }

}
