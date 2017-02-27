package fraud.detection.poc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BasketDisciminator {
    private static final String MINUTES_DISCRIMINATOR_NAME = "minute";
    private static final String HOUR_DISCRIMINATOR_NAME = "hour";
    private static final String DAY_OF_WEEK_DISCIRMINATOR_NAME = "dayOfWeek";
    private static final String DAY_OF_MONTH_DISCRIMINATOR_NAME = "dayOfMonth";
    private static final DateTimeFormatter formatterMinutes = DateTimeFormatter.ofPattern("mm");
    private static final DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("hh");
    private static final DateTimeFormatter formatterWeekDay = DateTimeFormatter.ofPattern("e");
    private static final DateTimeFormatter formatterDayOfMonth = DateTimeFormatter.ofPattern("d");

    private String name;
    private String value;

    private BasketDisciminator(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static BasketDisciminator createDailyBasketDesBasketDisciminator(LocalDateTime localDateTime) {
        return new BasketDisciminator(MINUTES_DISCRIMINATOR_NAME, localDateTime.format(formatterMinutes));
    }

    public static BasketDisciminator createHourlyBasketDesBasketDisciminator(LocalDateTime localDateTime) {
        return new BasketDisciminator(HOUR_DISCRIMINATOR_NAME, localDateTime.format(formatterHour));
    }

    public static BasketDisciminator createWeeklyBasketDesBasketDisciminator(LocalDateTime localDateTime) {
        return new BasketDisciminator(DAY_OF_WEEK_DISCIRMINATOR_NAME, localDateTime.format(formatterWeekDay));
    }

    public static BasketDisciminator createMonthlyBasketDesBasketDisciminator(LocalDateTime localDateTime) {
        return new BasketDisciminator(DAY_OF_MONTH_DISCRIMINATOR_NAME, localDateTime.format(formatterDayOfMonth));
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return value;
    }
}
