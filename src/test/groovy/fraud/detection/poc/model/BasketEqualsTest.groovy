package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime


class BasketEqualsTest extends Specification {
    LocalDateTime now;
    String basketName;

    def setup() {
        now = LocalDateTime.now()
        basketName = "basketName"
    }

    def "shoul be equals"() {
        given:
        Basket basketOne = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        Basket basketTwo = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        expect:
        basketOne.equals(basketTwo)

    }

    def "shoul not be equals when basketName is different"() {
        given:
        Basket basketOne = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        Basket basketTwo = new Basket(100, 1, basketName + "+", now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        expect:
        !basketOne.equals(basketTwo)
    }

    def "shoul not be equals when last update date is different"() {
        given:
        Basket basketOne = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        Basket basketTwo = new Basket(100, 1, basketName, now.plusDays(1), BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        expect:
        !basketOne.equals(basketTwo)
    }

    def "shoul not be equals when count is different"() {
        given:
        Basket basketOne = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        Basket basketTwo = new Basket(100, 2, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        expect:
        !basketOne.equals(basketTwo)
    }

    def "shoul not be equals when amount is different"() {
        given:
        Basket basketOne = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        Basket basketTwo = new Basket(1001, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        expect:
        !basketOne.equals(basketTwo)
    }

    def "should be equals after register new transaction"() {
        given:
        Basket basketOne = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        Basket basketTwo = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        when:
        basketOne.registerNewTransaction(100, now)
        basketTwo.registerNewTransaction(100, now)
        then:
        basketOne.equals(basketTwo)
    }

    def "should be not equals after register new transaction with difference amount"() {
        given:
        Basket basketOne = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        Basket basketTwo = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        when:
        basketOne.registerNewTransaction(100, now)
        basketTwo.registerNewTransaction(101, now)
        then:
        !basketOne.equals(basketTwo)
    }

    def "should be not equals after register new transaction with difference time"() {
        given:
        Basket basketOne = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        Basket basketTwo = new Basket(100, 1, basketName, now, BasketLineType.Daily.basketLastUpdateDateFormatterPattern)
        when:
        basketOne.registerNewTransaction(100, now)
        basketTwo.registerNewTransaction(100, now.plusDays(1))
        then:
        !basketOne.equals(basketTwo)
    }
}
