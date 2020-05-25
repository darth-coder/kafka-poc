package learning.kafkapoc.reader;

import learning.kafkapoc.domain.StockPrice;

import java.util.List;

public interface StockPriceReader {
    List<StockPrice> read();
}
