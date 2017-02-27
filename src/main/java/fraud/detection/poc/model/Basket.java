package fraud.detection.poc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Basket {

    private Double sumAmount = 0d;
    private Integer count = 0;
    private LocalDateTime lastUpdate;
    private String bucketName;
    private final DateTimeFormatter basketLastUpdateDateFormatterPattern;

    public Basket(Double amount, Integer count, String bucketName, LocalDateTime lastUpdate, String basketLastUpdateDateFormatterPattern) {
        this.lastUpdate = lastUpdate;
        this.bucketName = bucketName;
        this.count = count;
        this.sumAmount = amount;
        this.basketLastUpdateDateFormatterPattern = DateTimeFormatter.ofPattern(basketLastUpdateDateFormatterPattern);
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void registerNewTransaction(Double amount, LocalDateTime lu) {
        if (!basketLastUpdateDateFormatterPattern.format(lu).equals(basketLastUpdateDateFormatterPattern.format(this.getLastUpdate()))) {
            initializeData(amount);
        } else {
            this.sumAmount += amount;
            this.count++;
        }
        this.lastUpdate = lu;
    }

    private void initializeData(Double amount) {
        this.sumAmount = amount;
        this.count = 1;
    }

    public Integer getCount() {
        return count;
    }

    public Double getSumAmount() {
        return sumAmount;
    }

    public String getBucketName() {
        return bucketName;
    }

    public Double computeAverage() {
        return this.getSumAmount() / this.count;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "sumAmount=" + sumAmount +
                ", count=" + count +
                ", lastUpdate=" + lastUpdate +
                ", bucketName='" + bucketName + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket)) return false;

        Basket basket = (Basket) o;

        if (bucketName != null ? !bucketName.equals(basket.bucketName) : basket.bucketName != null) return false;
        if (count != null ? !count.equals(basket.count) : basket.count != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(basket.lastUpdate) : basket.lastUpdate != null) return false;
        return !(sumAmount != null ? !sumAmount.equals(basket.sumAmount) : basket.sumAmount != null);

    }

    @Override
    public int hashCode() {
        int result = sumAmount != null ? sumAmount.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (bucketName != null ? bucketName.hashCode() : 0);
        return result;
    }
}
