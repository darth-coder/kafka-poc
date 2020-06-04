package learning.kafkapoc.reader;

import learning.kafkapoc.domain.StockPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class KafkaStockPriceReaderTest {
    @Autowired
    @Qualifier("kafkaReader")
    StockPriceReader stockPriceReader;

    @Test
    public void test() {
        List<StockPrice> prices = stockPriceReader.read();
        Assertions.assertTrue(!prices.isEmpty());
    }

}
