package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by artur on 24.10.14.
 */
class AggregateDataAccountFindBacketTest extends Specification {
    def "FindBasketByName"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        AggregateDataAccount aggregateData = new AggregateDataAccount(123, now)
        when:
        List<Basket> list = aggregateData.getBasketLine(BasketLineType.Daily)
        Basket basket = aggregateData.findBasketByName(BasketLineType.Daily.getBasketName(now), list)
        then:
        basket != null

        when:
        list = aggregateData.getBasketLine(BasketLineType.Hourly)
        basket = aggregateData.findBasketByName(BasketLineType.Hourly.getBasketName(now), list)
        then:
        basket != null

        when:
        list = aggregateData.getBasketLine(BasketLineType.Weekly)
        basket = aggregateData.findBasketByName(BasketLineType.Weekly.getBasketName(now), list)
        then:
        basket != null

        when:
        list = aggregateData.getBasketLine(BasketLineType.Monthly)
        basket = aggregateData.findBasketByName(BasketLineType.Monthly.getBasketName(now), list)
        then:
        basket != null
    }
}
