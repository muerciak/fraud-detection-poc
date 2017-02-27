package fraud.detection.poc.json.assembler.vertx;

import fraud.detection.poc.model.Basket;
import fraud.detection.poc.model.BasketLineType;
import org.vertx.java.core.json.JsonObject;
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
        Timestamp timestamp = Timestamp.valueOf(basket.getLastUpdate());
        JsonObject jsonObject = new JsonObject();
        jsonObject
                .putString(BASKET_NAME_JSON_TEXT, basket.getBucketName())
                .putNumber(AMOUNT_JSON_TEXT, basket.getSumAmount())
                .putNumber(COUNT_JSON_TEXT, basket.getCount())
                .putNumber(LAST_UPDATE, timestamp.getTime());

        return jsonObject;
    }

    @Override
    public Basket convertFromJsonToObject(JsonObject jsonObject) {
        throw new NotImplementedException();
    }

    @Override
    public Basket convertFromJsonToObject(JsonObject jsonObject, BasketLineType basketLineType) {
        Integer count = (Integer) jsonObject.getNumber(COUNT_JSON_TEXT);
        Double amount = (Double) jsonObject.getNumber(AMOUNT_JSON_TEXT);
        Long lastUpdateMillis = jsonObject.getLong(LAST_UPDATE);
        String basketName = jsonObject.getString(BASKET_NAME_JSON_TEXT);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(lastUpdateMillis);
        LocalDateTime lastUpdate = timestamp.toLocalDateTime();

        return new Basket(amount, count, basketName, lastUpdate, basketLineType.basketLastUpdateDateFormatterPattern);
    }
}
