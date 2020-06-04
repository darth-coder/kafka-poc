package learning.kafkapoc.writer;

import learning.kafkapoc.dao.StockPriceDao;
import learning.kafkapoc.domain.StockPrice;
import learning.kafkapoc.reader.StockPriceDeserializer;
import learning.kafkapoc.utils.StockPriceTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class StockPriceWriterTest {

    @Autowired
    StockPriceDao stockPriceDao;

    @Autowired
    StockPriceWriter stockPriceWriter;

    @Test
    public void test() {
        StockPrice stockPrice = new StockPrice(StockPriceTestUtils.goog);
        stockPriceWriter.writeStockPrice(stockPrice);
        Assertions.assertEquals(stockPrice.getStockSymbol(), stockPriceDao.selectStockPrice(stockPrice.getStockSymbol(), stockPrice.getDate()).getStockSymbol());
    }

    @Test
    public void serializerTest() {
        StockPriceSerializer serializer = new StockPriceSerializer();
        byte[] data = serializer.serialize("", StockPriceTestUtils.goog);
        StockPriceDeserializer deserializer = new StockPriceDeserializer();
        StockPrice stockPrice = (StockPrice) deserializer.deserialize("", data);
        Assertions.assertTrue(stockPrice.equals(StockPriceTestUtils.goog));
    }
}
