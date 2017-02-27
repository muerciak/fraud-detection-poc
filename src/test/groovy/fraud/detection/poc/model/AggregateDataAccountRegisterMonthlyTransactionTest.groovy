package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime


class AggregateDataAccountRegisterMonthlyTransactionTest extends Specification {

    BasketLineType basketLineType = BasketLineType.Monthly

    def "RegisterNewMontlyTransaction"() {
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

    def "RegisterNewMontlyTransactionAfterOneDay"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        LocalDateTime nowPlusOneDay = now.plusDays(1)
        aggregateData.registerTransaction(100, nowPlusOneDay)
        then:
        Basket basket = aggregateData.findBasketByName(basketLineType.getBasketName(nowPlusOneDay), basketLineType)
        basket.count == 1
        aggregateData.getBasketLines().get(basketLineType).size() == 2
    }

    def "RegisterNewMontlyTransactionAfterOneMonth"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        LocalDateTime nowPlusOneMonth = now.plusMonths(1)
        aggregateData.registerTransaction(100, nowPlusOneMonth)
        then:
        Basket basket = aggregateData.findBasketByName(basketLineType.getBasketName(nowPlusOneMonth), basketLineType)
//        basket.count == 1
//        aggregateData.getBasketLines().get(basketLineType).size() == 1
    }
}
