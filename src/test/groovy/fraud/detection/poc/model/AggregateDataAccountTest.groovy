package fraud.detection.poc.model

import spock.lang.Specification

import java.time.LocalDateTime


class AggregateDataAccountTest extends Specification {

    AggregateDataAccount aggregateData
    LocalDateTime now

    def setup() {
        now = LocalDateTime.now()
        aggregateData = new AggregateDataAccount(100, now)
    }

    def "Compute"() {
        when:
        Double computeAverage = aggregateData.compute()
        then:
        computeAverage == 100

    }
}
