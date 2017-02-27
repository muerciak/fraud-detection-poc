package fraud.detection.poc.json.assembler.couchbase;

import com.couchbase.client.java.document.json.JsonObject;
import fraud.detection.poc.model.Basket;
import fraud.detection.poc.model.BasketLineType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class BasketJsonObjectAssembler implements JsonObjectAssembler<Basket> {

    private static final String AMOUNT_JSON_TEXT = "amount";
    private static final String BASKET_NAME_JSON_TEXT = "basketName";
    private static final String COUNT_JSON_TEXT = "count";
    private static final String LAST_UPDATE = "lastUpdate";

    @Override
    public JsonObject convertToJsonObject(Basket basket) {
        Instant instant = basket.getLastUpdate().atZone(ZoneOffset.ofHours(0)).toInstant();
        Timestamp timestamp = Timestamp.from(instant);
        JsonObject jsonObject = JsonObject.create();
        jsonObject
                .put(BASKET_NAME_JSON_TEXT, basket.getBucketName())
                .put(AMOUNT_JSON_TEXT, basket.getSumAmount())
                .put(COUNT_JSON_TEXT, basket.getCount())
                .put(LAST_UPDATE, timestamp.getTime());

        return jsonObject;
    }

    @Override
    public Basket convertFromJsonToObject(JsonObject jsonObject) {
        throw new NotImplementedException();
    }

    @Override
    public Basket convertFromJsonToObject(JsonObject jsonObject, BasketLineType basketLineType) {
        Integer count = jsonObject.getInt(COUNT_JSON_TEXT);
        Double amount = jsonObject.getDouble(AMOUNT_JSON_TEXT);
        Long lastUpdateMillis = jsonObject.getLong(LAST_UPDATE);
        String basketName = jsonObject.getString(BASKET_NAME_JSON_TEXT);
        Timestamp timestamp = new Timestamp(lastUpdateMillis);
        LocalDateTime lastUpdate = timestamp.toLocalDateTime();

        return new Basket(amount, count, basketName, lastUpdate, basketLineType.basketLastUpdateDateFormatterPattern);
    }
}
