package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by artur on 24.10.14.
 */
class BasketAmountTest extends Specification {

    def "shoul increase sum amount when add new amount"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        Basket basket = new Basket(123.03, 1, "test", now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        when:
        basket.registerNewTransaction(100, now)
        then:
        basket.sumAmount == 223.03
    }
}
