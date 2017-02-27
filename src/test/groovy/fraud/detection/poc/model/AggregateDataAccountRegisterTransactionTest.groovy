package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime

class AggregateDataAccountRegisterTransactionTest extends Specification {

    LocalDateTime now
    AggregateDataAccount aggregateData

    def setup() {
        now = LocalDateTime.now();
        aggregateData = new AggregateDataAccount(123.43, now);
    }

    def "should increase counter in all buckets"() {
        when:
        aggregateData.registerTransaction(100.00, now);
        then:
        aggregateData.getBasketLine(BasketLineType.Daily).get(0).getCount() == 2
        aggregateData.getBasketLine(BasketLineType.Hourly).get(0).getCount() == 2
        aggregateData.getBasketLine(BasketLineType.Monthly).get(0).getCount() == 2
        aggregateData.getBasketLine(BasketLineType.Weekly).get(0).getCount() == 2
    }

    def "should not add new basket to basketLine when register transaction in the same time"() {
        when:
        aggregateData.registerTransaction(100.00, now);
        then:
        aggregateData.getBasketLine(BasketLineType.Daily).size() == 1
        aggregateData.getBasketLine(BasketLineType.Hourly).size() == 1
        aggregateData.getBasketLine(BasketLineType.Monthly).size() == 1
        aggregateData.getBasketLine(BasketLineType.Weekly).size() == 1
    }

    def "should add new hourly basket when register new transaction after one minute"() {
        given:
        LocalDateTime nowPlusOneMinute = now.plusMinutes(1)
        when:
        aggregateData.registerTransaction(100.00, nowPlusOneMinute);
        then:
        aggregateData.getBasketLine(BasketLineType.Hourly).size() == 2
    }

    def "should add increase count and amount in Daily basketLine when register new transaction after one minute"() {
        given:
        LocalDateTime nowPlusOneMinute = now.plusMinutes(1)
        when:
        aggregateData.registerTransaction(100.00, nowPlusOneMinute);
        then:
        aggregateData.getBasketLine(BasketLineType.Daily).size() == 1
        aggregateData.getBasketLine(BasketLineType.Daily).get(0).getCount() == 2
        aggregateData.getBasketLine(BasketLineType.Daily).get(0).getSumAmount() == 223.43
    }
}
