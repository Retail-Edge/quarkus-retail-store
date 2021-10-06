package com.redhat.demos.quarkusretailstore.inventory;

import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.ui.api.InventoryJson;
import com.redhat.demos.quarkusretailstore.utils.JsonUtil;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@TestProfile(InventoryResourceTestProfile.class)
public class InventoryResourceTest {

    static final String skuId = UUID.randomUUID().toString();

    @Test @Order(3)
    public void testCompleteInventory() {

        RestAssured.registerParser(MediaType.APPLICATION_JSON, Parser.JSON);

        given()
                .when().get("/inventory/v2")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(3)));
    }

    @Test @Order(2)
    public void testUpdatingInventory() {

        with().body(JsonUtil.toJson(new InventoryJson(
                        skuId,
                        Double.valueOf(19.99),
                        Double.valueOf(24.99),
                        1,
                        9,
                        0,
                        Calendar.getInstance().getTime(),
                        Calendar.getInstance().getTime(),
                        10,
                        15,
                12
                )))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("PUT", "/inventory")
                .then()
                .statusCode(200);
    }

    @Test @Order(1)
    public void testAddingInventory() {

        System.out.println(JsonUtil.toJson(new InventoryJson(
                skuId,
                Double.valueOf(19.99),
                Double.valueOf(24.99),
                1,
                9,
                0,
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime(),
                10,
                15,
                12
        )));

        with().body(JsonUtil.toJson(new InventoryJson(
                        skuId,
                        Double.valueOf(19.99),
                        Double.valueOf(24.99),
                        1,
                        9,
                        0,
                        Calendar.getInstance().getTime(),
                        Calendar.getInstance().getTime(),
                        10,
                        15,
                    12
                )))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/inventory/v2")
                .then()
                .statusCode(201);
    }
}
