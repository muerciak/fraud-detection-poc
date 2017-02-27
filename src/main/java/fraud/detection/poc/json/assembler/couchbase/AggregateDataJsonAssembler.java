package fraud.detection.poc.json.assembler.couchbase;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import fraud.detection.poc.model.AggregateDataAccount;
import fraud.detection.poc.model.Basket;
import fraud.detection.poc.model.BasketLineType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;

public class AggregateDataJsonAssembler implements JsonObjectAssembler<AggregateDataAccount> {

    BasketLineJsonAssembler basketLineJsonAssembler = new BasketLineJsonAssembler();

    public JsonObject convertToJsonObject(AggregateDataAccount aggregateDataAccount) {
        JsonObject jsonObject = JsonObject.create();

        for (BasketLineType basketLineType : aggregateDataAccount.getBasketLines().keySet()) {
            jsonObject.put(basketLineType.name(), basketLineJsonAssembler.convertToJsonObject(aggregateDataAccount.getBasketLine(basketLineType)));
        }
        return jsonObject;
    }

    public AggregateDataAccount convertFromJsonToObject(JsonObject jsonObject) {
        AggregateDataAccount aggregateDataAccount = new AggregateDataAccount();

        Arrays.stream(BasketLineType.values()).forEach(basketLineType -> {
            JsonArray jsonArray = jsonObject.getArray(basketLineType.name());
            List<Basket> basketLine = basketLineJsonAssembler.convertFromJsonToObject(jsonArray, basketLineType);
            aggregateDataAccount.addBasketLine(basketLine, basketLineType);
        });

        return aggregateDataAccount;
    }

    @Override
    public AggregateDataAccount convertFromJsonToObject(JsonObject jsonObject, BasketLineType basketLineType) {
        throw new NotImplementedException();
    }
}
