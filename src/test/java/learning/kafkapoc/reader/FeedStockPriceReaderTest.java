package learning.kafkapoc.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeedStockPriceReaderTest {

    @Autowired
    StockPriceReader stockPriceReader;

    @Test
    public void test() {
        Assertions.assertTrue(!stockPriceReader.read().isEmpty());
    }
}
