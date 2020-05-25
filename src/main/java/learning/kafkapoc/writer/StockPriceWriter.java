package learning.kafkapoc.writer;

import learning.kafkapoc.domain.StockPrice;

public interface StockPriceWriter {
    void writeStockPrice(StockPrice stockPrice);
}
