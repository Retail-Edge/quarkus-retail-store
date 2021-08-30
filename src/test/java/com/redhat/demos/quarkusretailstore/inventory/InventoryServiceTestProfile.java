package com.redhat.demos.quarkusretailstore.inventory;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.*;

public class InventoryServiceTestProfile implements QuarkusTestProfile {

    @Override
    public Set<Class<?>> getEnabledAlternatives() {
        Set enabledAlternatives = new HashSet();
        enabledAlternatives.add(MockProductMasterRepository.class);
        enabledAlternatives.add(MockInventoryRepository.class);
        return enabledAlternatives;
    }

}
