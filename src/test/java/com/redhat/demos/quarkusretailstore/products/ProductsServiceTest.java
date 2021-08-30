package com.redhat.demos.quarkusretailstore.products;

import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.api.ProductsService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ProductsServiceTest {

    @Inject
    ProductsService productsService;

    @Inject
    ProductMasterRepository productMasterRepository;

    @BeforeEach @Transactional
    public void setUp() {
        productMasterRepository.persist(new ProductMaster("A product"));
        productMasterRepository.persist(new ProductMaster("A second product"));
        productMasterRepository.persist(new ProductMaster("A third product"));
        productMasterRepository.persist(new ProductMaster("A fourth product"));
    }

    @Test
    public void testAllProducts() {

        Collection<ProductMasterDTO> results = productsService.getAllProducts();
        assertNotNull(results);
        assertEquals(4, results.size());

    }
}
