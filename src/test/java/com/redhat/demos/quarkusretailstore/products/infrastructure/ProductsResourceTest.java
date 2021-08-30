package com.redhat.demos.quarkusretailstore.products.infrastructure;

import com.redhat.demos.quarkusretailstore.products.api.ProductsService;
import com.redhat.demos.quarkusretailstore.ui.api.ProductMasterJson;
import com.redhat.demos.quarkusretailstore.utils.JsonUtil;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestProfile(ProductsResourceTestProfile.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
/**
 * This class tests the ProductResource in Isolation.  All downstream actions are mocked.
 * 
 * @see com.redhat.demos.quarkusretailstore.products.MockProductsService
 */
public class ProductsResourceTest {

    @Inject
    private ProductsService productsService;

    @Order(2)
    @Test
    public void testProductsEndpoint() {

        RestAssured.registerParser(MediaType.APPLICATION_JSON, Parser.JSON);

        given()
                .when().get("/products")
                .then()
                .statusCode(200)
                .body("$", hasSize(3));
    }

    @Order(1)
    @Test
    public void testAddingAProduct() {

        ProductMasterJson productMasterJson = new ProductMasterJson("A test product");

        with()
                .body(JsonUtil.toJson(productMasterJson))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/products/")
                .then()
                .statusCode(201)
                .body("description", equalTo("A test product"));
    }

}
