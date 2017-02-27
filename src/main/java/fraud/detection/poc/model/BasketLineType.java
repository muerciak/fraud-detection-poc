package fraud.detection.poc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum BasketLineType {
    Daily("hour", "hh", "yyyy-MM-dd-hh"), Hourly("minute", "mm", "yyyy-MM-dd-hh-mm"), Weekly("dayOfWeek", "e", "yyyy-MM-dd"), Monthly("dayOfMonth", "d", "yyyy-MM");
    private String type;
    private DateTimeFormatter dateTimeFormatter;
    public String basketLastUpdateDateFormatterPattern;

    BasketLineType(String type, String pattern, String basketLastUpdateDateFormatterPattern) {
        this.type = type;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        this.basketLastUpdateDateFormatterPattern = basketLastUpdateDateFormatterPattern;
    }

    public String getBasketName(LocalDateTime ldt) {
        return this.dateTimeFormatter.format(ldt);
    }

    public String getType() {
        return type;
    }

}
