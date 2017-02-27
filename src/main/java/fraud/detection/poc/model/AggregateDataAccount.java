package fraud.detection.poc.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import fraud.detection.poc.exception.ComputeException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class AggregateDataAccount {

    private final ListMultimap<BasketLineType, Basket> basketLines = ArrayListMultimap.create();

    public AggregateDataAccount() {
    }

    public AggregateDataAccount(Double amount, LocalDateTime lastUpdate) {
        for (BasketLineType basketLineType : BasketLineType.values()) {
            this.basketLines.put(basketLineType, new Basket(amount, 1, basketLineType.getBasketName(lastUpdate), lastUpdate, basketLineType.basketLastUpdateDateFormatterPattern));
        }
    }

    void addBasket(BasketLineType basketLineType, Basket basket) {
        this.basketLines.put(basketLineType, basket);
    }

    public ListMultimap<BasketLineType, Basket> getBasketLines() {
        return basketLines;
    }

    public List<Basket> getBasketLine(BasketLineType basketLineType) {
        return basketLines.get(basketLineType);
    }

    public void addBasketLine(List<Basket> basketLine, BasketLineType type) {
        this.basketLines.putAll(type, basketLine);
    }

    public void registerTransaction(Double amount, LocalDateTime localDateTime) {
        Stream.of(BasketLineType.values()).forEach(basketLineType -> {
            Basket basket;
            String basketName = basketLineType.getBasketName(localDateTime);
            if (checkBasketIfExists(basketName, basketLineType)) {
                basket = findBasketByName(basketName, basketLineType);
                basket.registerNewTransaction(amount, localDateTime);
            } else {
                basket = new Basket(amount, 1, basketName, localDateTime, basketLineType.basketLastUpdateDateFormatterPattern);
                addBasket(basketLineType, basket);
            }
        });
    }

    private boolean checkBasketIfExists(String basketName, BasketLineType basketLineType) {
        return basketLines.get(basketLineType).stream().anyMatch(basket -> basket.getBucketName().equals(basketName));
    }

    public Basket findBasketByName(String name, BasketLineType basketLineType) {
        return findBasketByName(name, basketLines.get(basketLineType));
    }

    public Basket findBasketByName(String name, List<Basket> basketList) {

        return basketList
                .stream()
                .filter(basket -> basket.getBucketName().equals(name))
                .findFirst()
                .get();
    }

    //fake froud detection algoritm
    public Double compute() throws ComputeException {
        Double sumAverage = 0d;
        for (BasketLineType basketLineType : BasketLineType.values()) {
            sumAverage += basketLines.get(basketLineType).stream().mapToDouble(basket -> basket.computeAverage()).average().getAsDouble();
        }
        return (sumAverage / BasketLineType.values().length);
    }

}
