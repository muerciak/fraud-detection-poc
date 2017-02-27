package fraud.detection.poc.json.assembler.couchbase;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import fraud.detection.poc.model.Basket;
import fraud.detection.poc.model.BasketLineType;

import java.util.ArrayList;
import java.util.List;

public class BasketLineJsonAssembler implements JsonArrayAssembler<List<Basket>> {

    BasketJsonObjectAssembler basketJsonObjectAssembler = new BasketJsonObjectAssembler();

    @Override
    public JsonArray convertToJsonObject(List<Basket> baskets) {
        JsonArray array = JsonArray.create();
        baskets.forEach(basket -> array.add(basketJsonObjectAssembler.convertToJsonObject(basket)));
        return array;
    }

    @Override
    public List<Basket> convertFromJsonToObject(JsonArray jsonArray, BasketLineType basketType) {
        List<Basket> basketLine = new ArrayList<>();
        jsonArray.toList().forEach(jsonObject -> basketLine.add(basketJsonObjectAssembler.convertFromJsonToObject((JsonObject) jsonObject)));
        return basketLine;
    }
}
