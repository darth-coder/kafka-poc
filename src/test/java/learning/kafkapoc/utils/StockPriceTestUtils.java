package learning.kafkapoc.utils;

import learning.kafkapoc.domain.StockPrice;

import java.time.LocalDate;

public class StockPriceTestUtils {
    public static StockPrice goog = new StockPrice("GOOG", LocalDate.of(2020, 5, 17), 100.0);
    public static StockPrice msft = new StockPrice("MSFT", LocalDate.of(2020, 5, 17), 100.0);
}
