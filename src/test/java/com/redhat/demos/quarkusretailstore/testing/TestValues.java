package com.redhat.demos.quarkusretailstore.testing;

import java.util.UUID;

/**
 * Values that can be used across tests
 */
public class TestValues {

    public static final String STORE_ID = "ATLANTA-01";

    public static final String INVOICE_ID = UUID.randomUUID().toString();

    public static final String INVOICE_ID_02 = UUID.randomUUID().toString();

    public static final String INVOICE_LINE_ID = UUID.randomUUID().toString();

    public static final String SKU_ID = UUID.randomUUID().toString();

    public static final String SKU_ID_02 = UUID.randomUUID().toString();

    public static final String SKU_DESCRIPTION = "A Product";

    public static final String CUSTOMER_NAME = "Tigger";

}
