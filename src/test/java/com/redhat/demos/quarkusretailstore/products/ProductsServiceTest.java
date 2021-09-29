package com.redhat.demos.quarkusretailstore.products;

import com.redhat.demos.quarkusretailstore.products.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.api.ProductsService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ProductsServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsServiceTest.class);

    @Inject
    ProductsService productsService;

    @Inject
    ProductMasterRepository productMasterRepository;

    Long expectedRecords;

    @BeforeEach @Transactional
    public void setUp() {

        long count = productMasterRepository.listAll().stream().count();
        LOGGER.info("current product count: {}", count);

        productMasterRepository.persist(new ProductMaster("A product"));
        productMasterRepository.persist(new ProductMaster("A second product"));
        productMasterRepository.persist(new ProductMaster("A third product"));
        productMasterRepository.persist(new ProductMaster("A fourth product"));
        productMasterRepository.flush();
        expectedRecords = count + 4;
        LOGGER.info("expected product count: {}", expectedRecords);
    }

    @Test
    public void testAllProducts() {

        Collection<ProductMasterDTO> results = productsService.getAllProducts();
        assertNotNull(results);
        results.forEach(productMasterDTO -> {
            LOGGER.debug("next: {}", productMasterDTO);
        });
        assertEquals(expectedRecords.intValue(), productsService.getAllProducts().stream().count());

    }
}
