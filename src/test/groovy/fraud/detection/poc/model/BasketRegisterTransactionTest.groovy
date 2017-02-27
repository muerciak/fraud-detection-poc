package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime


class BasketRegisterTransactionTest extends Specification {

    def "RegisterNewDailyTransactionInTheSameTime"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        Basket basket = new Basket(100, 1, BasketLineType.Daily.getBasketName(now), now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        when:
        basket.registerNewTransaction(123, now)
        then:
        basket.count == 2
        basket.sumAmount == 223
    }

    def "RegisterNewDailyTransactionInNewTime"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        Basket basket = new Basket(100, 1, BasketLineType.Hourly.getBasketName(now), now, BasketLineType.Hourly.basketLastUpdateDateFormatterPattern)
        when:
        LocalDateTime nowPlusOneMinute = now.plusMinutes(1)
        basket.registerNewTransaction(123, nowPlusOneMinute)
        then:
        basket.count == 1
        basket.sumAmount == 123
    }
}
