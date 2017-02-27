package fraud.detection.poc.json.assembler.vertx

import fraud.detection.poc.model.AggregateDataAccount
import org.vertx.java.core.json.JsonObject
import spock.lang.Specification

import java.time.LocalDateTime


class AggregateDataAccountJsonAssemblerTest extends Specification {


    AggregateDataJsonAssembler aggregateDataJsonAssembler = new AggregateDataJsonAssembler();

    void testConvertToJsonObject() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(100, now)
        LocalDateTime nowPlustOneHour = now.plusHours(1)
        aggregateData.registerTransaction(200, nowPlustOneHour)
        JsonObject jsonObject = aggregateDataJsonAssembler.convertToJsonObject(aggregateData)
        when:
        AggregateDataAccount aggregateDataAfterTrans = aggregateDataJsonAssembler.convertFromJsonToObject(jsonObject)
        then:
        aggregateDataAfterTrans.getBasketLines().equals(aggregateData.getBasketLines())

    }

    void testConvertFromJsonToObject() {

    }
}
