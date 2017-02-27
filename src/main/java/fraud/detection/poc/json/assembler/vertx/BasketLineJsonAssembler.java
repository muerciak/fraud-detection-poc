package fraud.detection.poc.json.assembler.vertx;

import fraud.detection.poc.model.Basket;
import fraud.detection.poc.model.BasketLineType;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class BasketLineJsonAssembler implements JsonArrayAssembler<List<Basket>> {

    private BasketJsonObjectAssembler basketJsonObjectAssembler = new BasketJsonObjectAssembler();

    @Override
    public JsonArray convertToJsonObject(List<Basket> baskets) {
        JsonArray array = new JsonArray();
        baskets.forEach(basket -> {
            JsonObject jsonObject = basketJsonObjectAssembler.convertToJsonObject(basket);
            array.addObject(jsonObject);
        });
        return array;
    }

    @Override
    public List<Basket> convertFromJsonToObject(JsonArray jsonArray, BasketLineType basketType) {
        List<Basket> basketLine = new ArrayList<>();
        jsonArray.forEach(jsonObject -> {
            basketLine.add(basketJsonObjectAssembler.convertFromJsonToObject((JsonObject) jsonObject, basketType));
        });
        return basketLine;
    }
}
