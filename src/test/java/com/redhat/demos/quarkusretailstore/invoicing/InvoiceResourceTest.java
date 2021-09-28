package com.redhat.demos.quarkusretailstore.invoicing;

import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.api.ProductsService;
import com.redhat.demos.quarkusretailstore.ui.api.InvoiceHeaderJson;
import com.redhat.demos.quarkusretailstore.ui.api.InvoiceJson;
import com.redhat.demos.quarkusretailstore.ui.api.InvoiceLineJson;
import com.redhat.demos.quarkusretailstore.ui.api.ProductMasterJson;
import com.redhat.demos.quarkusretailstore.utils.JsonUtil;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static io.restassured.RestAssured.with;

@QuarkusTest
public class InvoiceResourceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceResourceTest.class);

    @Inject
    ProductsService productsService;

    String skuId;

    @BeforeEach @Transactional
    public void setUp() {

        ProductMaster productMaster = new ProductMaster(UUID.randomUUID().toString(), "A Test Product");
        productMaster.persistAndFlush();
        LOGGER.info("persisted {}", productMaster);
        skuId = productMaster.getSkuId();
    }

    @Test
    public void testCreateInvoice() {

        InvoiceHeaderJson invoiceHeaderJson = new InvoiceHeaderJson(UUID.randomUUID().toString(), Date.from(Instant.now()), 35.99, 2);
        Collection<InvoiceLineJson> invoiceLineJsonCollection = new ArrayList(
                Arrays.asList(new InvoiceLineJson(skuId, Double.valueOf(1), Double.valueOf(25.99), Double.valueOf(25.99), UnitOfMeasure.EACH))
        );
        InvoiceJson invoiceJson = new InvoiceJson(UUID.randomUUID().toString(), invoiceHeaderJson, invoiceLineJsonCollection, "Eeyore");

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
