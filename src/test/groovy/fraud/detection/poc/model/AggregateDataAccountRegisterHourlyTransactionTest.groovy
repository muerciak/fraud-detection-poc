package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime


class AggregateDataAccountRegisterHourlyTransactionTest extends Specification {

    def "RegisterNewMinuteTransaction"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        aggregateData.registerTransaction(100, now)
        then:
        Basket basket = aggregateData.findBasketByName(BasketLineType.Hourly.getBasketName(now), BasketLineType.Hourly)
        basket.count == 2
        aggregateData.getBasketLines().get(BasketLineType.Hourly).size() == 1
    }

    def "RegisterNewMinuteTransactionAfterOneMinute"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        LocalDateTime nowPlusOneMinute = now.plusMinutes(1);
        aggregateData.registerTransaction(100, nowPlusOneMinute)
        Basket basket = aggregateData.findBasketByName(BasketLineType.Hourly.getBasketName(nowPlusOneMinute), BasketLineType.Hourly)
        then:
        basket.count == 1
        aggregateData.getBasketLines().get(BasketLineType.Hourly).size() == 2
    }

    def "RegisterNewMinuteTransactionAfterSixtyMinutes"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        LocalDateTime nowPlusOneMinute = now.plusMinutes(60);
        aggregateData.registerTransaction(100, nowPlusOneMinute)
        Basket basket = aggregateData.findBasketByName(BasketLineType.Hourly.getBasketName(nowPlusOneMinute), BasketLineType.Hourly)
        then:
        basket.count == 1
        aggregateData.getBasketLines().get(BasketLineType.Hourly).size() == 1
        basket.sumAmount == 100
    }
}
