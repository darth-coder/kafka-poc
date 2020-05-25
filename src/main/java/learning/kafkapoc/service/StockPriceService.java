package learning.kafkapoc.service;

import learning.kafkapoc.reader.StockPriceReader;
import learning.kafkapoc.writer.StockPriceWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockPriceService {

    @Autowired
    StockPriceReader stockPriceReader;

    @Autowired
    StockPriceWriter stockPriceWriter;

    public void run(){
        stockPriceReader.read().forEach(e -> stockPriceWriter.writeStockPrice(e));
    }

}
