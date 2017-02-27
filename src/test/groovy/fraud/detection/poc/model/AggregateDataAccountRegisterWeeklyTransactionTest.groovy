package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime


class AggregateDataAccountRegisterWeeklyTransactionTest extends Specification {

    BasketLineType basketLineType = BasketLineType.Weekly

    def "RegisterNewWeeklyTransaction"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        aggregateData.registerTransaction(100, now)
        then:
        Basket basket = aggregateData.findBasketByName(basketLineType.getBasketName(now), basketLineType)
        basket.count == 2
        aggregateData.getBasketLines().get(basketLineType).size() == 1
    }

    def "RegisterNewWeeklyTransactionAfterOneWeekDay"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        LocalDateTime nowPlusOneWeekDay = now.plusDays(1)
        aggregateData.registerTransaction(100, nowPlusOneWeekDay)
        then:
        Basket basket = aggregateData.findBasketByName(basketLineType.getBasketName(nowPlusOneWeekDay), basketLineType)
        basket.count == 1
        aggregateData.getBasketLines().get(basketLineType).size() == 2
    }

    def "RegisterNewWeeklyTransactionAfterOneWeek"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        LocalDateTime nowPlusOneWeekDay = now.plusWeeks(1)
        aggregateData.registerTransaction(100, nowPlusOneWeekDay)
        then:
        Basket basket = aggregateData.findBasketByName(basketLineType.getBasketName(nowPlusOneWeekDay), basketLineType)
        basket.count == 1
        aggregateData.getBasketLines().get(basketLineType).size() == 1
    }
}
