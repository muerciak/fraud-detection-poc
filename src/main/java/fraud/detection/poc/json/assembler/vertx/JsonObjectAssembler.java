package fraud.detection.poc.json.assembler.vertx;

import fraud.detection.poc.model.BasketLineType;
import org.vertx.java.core.json.JsonObject;

public interface JsonObjectAssembler<T> {

    JsonObject convertToJsonObject(T t);

    T convertFromJsonToObject(JsonObject jsonObject);

    T convertFromJsonToObject(JsonObject jsonObject, BasketLineType basketLineType);
}
