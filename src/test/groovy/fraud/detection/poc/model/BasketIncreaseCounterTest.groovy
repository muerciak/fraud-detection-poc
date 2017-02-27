package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by artur on 24.10.14.
 */
class BasketIncreaseCounterTest extends Specification {


    def "shold increase counter when add new amount"() {
        given:
        LocalDateTime now = LocalDateTime.now();
        Basket basket = new Basket(123.0, 1, "1", now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern);
        when:
        now = LocalDateTime.now();
        basket.registerNewTransaction(10.0, now);
        then:
        basket.count == 2;

        when:
        basket.registerNewTransaction(12.3, now)
        then:
        basket.count == 3;

    }
}
