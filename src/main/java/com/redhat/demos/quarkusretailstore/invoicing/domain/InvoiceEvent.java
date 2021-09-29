package com.redhat.demos.quarkusretailstore.invoicing.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.demos.quarkusretailstore.utils.JsonUtil;
import io.debezium.outbox.quarkus.ExportedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class InvoiceEvent implements ExportedEvent<String, JsonNode>{

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceEvent.class);

    private static ObjectMapper mapper = new ObjectMapper();

    private static final String TYPE = "Order";
    private static final String EVENT_TYPE = "OrderCreated";

    private final String invoiceId;
    private final JsonNode jsonNode;
    private final Instant timestamp;

    private InvoiceEvent(String invoiceId, JsonNode jsonNode, Instant timestamp) {
        this.invoiceId = invoiceId;
        this.jsonNode = jsonNode;
        this.timestamp = timestamp;
    }

    public static InvoiceEvent from(final InvoiceRecord invoiceRecord) {

        LOGGER.debug("Creating InvoiceEvent from: {}", invoiceRecord);

        ObjectNode asJson = mapper.createObjectNode()
                .put("invoiceId", invoiceRecord.getInvoiceId())
                .put("invoice", JsonUtil.toJson(invoiceRecord));

        return new InvoiceEvent(invoiceRecord.invoiceId, asJson, invoiceRecord.getInvoiceHeader().date.toInstant());
    }

    @Override
    public String getAggregateId() {
        return invoiceId;
    }

    @Override
    public String getAggregateType() {
        return TYPE;
    }

    @Override
    public JsonNode getPayload() {
        return jsonNode;
    }

    @Override
    public String getType() {
        return EVENT_TYPE;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }
}
