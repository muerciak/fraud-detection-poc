package fraud.detection.poc.model

import spock.lang.Specification
import java.time.LocalDateTime


class AggregateDataAccountRegisterDailyTransactionTest extends Specification {

    BasketLineType basketLineType = BasketLineType.Daily

    def "RegisterNewDailyTransaction"() {
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

    def "RegisterNewDailyTransactionAfterOneHour"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        LocalDateTime nowPlusOneHour = now.plusHours(1);
        aggregateData.registerTransaction(100, nowPlusOneHour)
        Basket basket = aggregateData.findBasketByName(basketLineType.getBasketName(nowPlusOneHour), basketLineType)
        then:
        basket.count == 1
        aggregateData.getBasketLines().get(basketLineType).size() == 2
    }

    def "RegisterNewMinuteTransactionAfter24Hours"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        LocalDateTime nowPlus24H = now.plusHours(24);
        aggregateData.registerTransaction(100, nowPlus24H)
        Basket basket = aggregateData.findBasketByName(basketLineType.getBasketName(nowPlus24H), basketLineType)
        then:
        basket.count == 1
        aggregateData.getBasketLines().get(basketLineType).size() == 1
        basket.sumAmount == 100
    }

}
