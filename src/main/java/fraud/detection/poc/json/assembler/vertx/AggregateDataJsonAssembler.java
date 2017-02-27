package fraud.detection.poc.json.assembler.vertx;

import fraud.detection.poc.model.AggregateDataAccount;
import fraud.detection.poc.model.Basket;
import fraud.detection.poc.model.BasketLineType;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.Arrays;
import java.util.List;

import static fraud.detection.poc.model.BasketLineType.values;


public class AggregateDataJsonAssembler implements JsonObjectAssembler<AggregateDataAccount> {

    private BasketLineJsonAssembler basketLineJsonAssembler = new BasketLineJsonAssembler();

    public JsonObject convertToJsonObject(AggregateDataAccount aggregateDataAccount) {
        JsonObject jsonObject = new JsonObject();
        aggregateDataAccount.getBasketLines().keySet().forEach(basketLineType -> {
            jsonObject.putElement(basketLineType.name(), basketLineJsonAssembler.convertToJsonObject(aggregateDataAccount.getBasketLine(basketLineType)));
        });
        return jsonObject;
    }

    public AggregateDataAccount convertFromJsonToObject(JsonObject jsonObject) {
        AggregateDataAccount aggregateDataAccount = new AggregateDataAccount();
        Arrays.stream(values()).forEach(basketLineType -> {
            JsonArray jsonArray = jsonObject.getArray(basketLineType.name());
            List<Basket> basketLine = basketLineJsonAssembler.convertFromJsonToObject(jsonArray, basketLineType);
            aggregateDataAccount.addBasketLine(basketLine, basketLineType);
        });
        return aggregateDataAccount;
    }

    @Override
    public AggregateDataAccount convertFromJsonToObject(JsonObject jsonObject, BasketLineType basketLineType) {
        throw new RuntimeException("Not implemented yet");
    }
}
