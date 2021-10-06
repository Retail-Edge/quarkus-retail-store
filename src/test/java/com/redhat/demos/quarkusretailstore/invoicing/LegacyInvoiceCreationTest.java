package com.redhat.demos.quarkusretailstore.invoicing;

import com.redhat.demos.quarkusretailstore.invoicing.api.LegacyInvoiceJson;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.testing.TestValues;
import com.redhat.demos.quarkusretailstore.ui.api.*;
import com.redhat.demos.quarkusretailstore.utils.JsonUtil;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.with;

@QuarkusTest
public class LegacyInvoiceCreationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LegacyInvoiceCreationTest.class);

    @BeforeAll
    @Transactional
    static void setUp() {

        ProductMaster productMaster = new ProductMaster(TestValues.SKU_ID, TestValues.SKU_DESCRIPTION);
        productMaster.persistAndFlush();
    }

    @Test
    public void testCreateInvoice() {

        InvoiceHeaderJson invoiceHeaderJson = new InvoiceHeaderJson(TestValues.STORE_ID, Date.from(Instant.now()), 35.99, 2);
        Collection<LegacyInvoiceLineJson> invoiceLineJsonCollection = new ArrayList(
                Arrays.asList(new LegacyInvoiceLineJson(new ProductMasterJson(TestValues.SKU_ID, TestValues.SKU_DESCRIPTION), Double.valueOf(1), Double.valueOf(25.99), UnitOfMeasure.EACH))
        );
        LegacyInvoiceJson invoiceJson = new LegacyInvoiceJson(TestValues.INVOICE_ID, invoiceHeaderJson, invoiceLineJsonCollection, "Eeyore");

        LOGGER.debug("Testing with: {}", JsonUtil.toJson(invoiceJson));

        with().body(invoiceJson)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/invoicing")
                .then()
                .statusCode(201);

    }
}
