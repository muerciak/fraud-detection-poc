package fraud.detection.poc.json.assembler.couchbase;

import com.couchbase.client.java.document.json.JsonArray;
import fraud.detection.poc.model.BasketLineType;

public interface JsonArrayAssembler<T> {
    JsonArray convertToJsonObject(T t);

    T convertFromJsonToObject(JsonArray jsonObject, BasketLineType basketType);
}
