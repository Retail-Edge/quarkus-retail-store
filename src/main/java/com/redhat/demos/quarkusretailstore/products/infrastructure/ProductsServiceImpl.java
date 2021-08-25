package com.redhat.demos.quarkusretailstore.products.infrastructure;

import com.redhat.demos.quarkusretailstore.invoicing.api.ProductMasterDTO;
import com.redhat.demos.quarkusretailstore.products.NoSuchProductException;
import com.redhat.demos.quarkusretailstore.products.ProductMaster;
import com.redhat.demos.quarkusretailstore.products.ProductMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
public class ProductsServiceImpl implements ProductsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsServiceImpl.class);

    @Inject
    ProductMasterRepository productMasterRepository;

    @Override
    public Collection<ProductMaster> getAllProducts() {

        return productMasterRepository.listAll();
    }

    @Override
    public ProductMaster getProductBySkuId(String skuId) throws NoSuchProductException {
        return productMasterRepository.find("skuId", skuId).singleResult();
    }

    @Override @Transactional
    public ProductMasterDTO addProduct(ProductMasterDTO productMasterDTO) {

        LOGGER.debug("adding: {}", productMasterDTO);
        ProductMaster productMaster = new ProductMaster(UUID.randomUUID().toString(), productMasterDTO.getDescription());
        productMasterRepository.persist(productMaster);
        LOGGER.debug("added: {}", productMaster);
        return new ProductMasterDTO(productMaster.getSkuId(), productMasterDTO.getDescription());
    }

/*
    Collection<ProductMaster> productMasters = new ArrayList(
            Arrays.asList(new ProductMaster(UUID.randomUUID().toString(), "A product"),
                    new ProductMaster(UUID.randomUUID().toString(), "Another product"),
                    new ProductMaster(UUID.randomUUID().toString(), "Yet another product"),
                    new ProductMaster(UUID.randomUUID().toString(), "One more product"))
    );

    @Override
    public Collection<ProductMaster> getAllProducts() {
        return productMasters;
    }

    @Override
    public ProductMaster getProductById(String skuId) throws NoSuchProductException {
        return new ProductMaster(skuId, "A product");
    }
*/
}
