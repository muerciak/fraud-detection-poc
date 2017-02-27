package fraud.detection.poc.json.assembler.vertx;

import fraud.detection.poc.model.BasketLineType;
import org.vertx.java.core.json.JsonArray;

public interface JsonArrayAssembler<T> {
    JsonArray convertToJsonObject(T t);

    T convertFromJsonToObject(JsonArray jsonObject, BasketLineType basketType);
}
