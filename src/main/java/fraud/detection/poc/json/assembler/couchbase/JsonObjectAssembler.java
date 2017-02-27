package fraud.detection.poc.json.assembler.couchbase;

import com.couchbase.client.java.document.json.JsonObject;
import fraud.detection.poc.model.BasketLineType;

public interface JsonObjectAssembler<T> {
    JsonObject convertToJsonObject(T t);

    T convertFromJsonToObject(JsonObject jsonObject);

    T convertFromJsonToObject(JsonObject jsonObject, BasketLineType basketLineType);
}
