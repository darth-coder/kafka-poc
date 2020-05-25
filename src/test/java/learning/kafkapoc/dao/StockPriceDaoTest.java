package learning.kafkapoc.dao;

import learning.kafkapoc.domain.StockPrice;
import learning.kafkapoc.utils.StockPriceTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;

@Transactional
@SpringBootTest
public class StockPriceDaoTest {

    @Inject
    StockPriceDao stockPriceDao;

    StockPrice goog = new StockPrice(StockPriceTestUtils.goog);
    StockPrice msft = new StockPrice(StockPriceTestUtils.msft);

    @Test
    void insert(){
        stockPriceDao.deleteStockPrice(goog.getStockSymbol(), goog.getDate());
        stockPriceDao.insertStockPrice(goog);
        Assertions.assertNotNull(stockPriceDao.selectStockPrice(goog.getStockSymbol(), goog.getDate()));
    }

    @Test
    void delete(){
        stockPriceDao.deleteStockPrice(msft.getStockSymbol(), msft.getDate());
        stockPriceDao.insertStockPrice(msft);
        Assertions.assertNotNull(stockPriceDao.selectStockPrice(msft.getStockSymbol(), msft.getDate()));
        stockPriceDao.deleteStockPrice(msft.getStockSymbol(), msft.getDate());
        Assertions.assertNull(stockPriceDao.selectStockPrice(msft.getStockSymbol(), msft.getDate()));
    }

    @Test
    void getAll(){
        stockPriceDao.selectAllStockPrice().forEach(e -> stockPriceDao.deleteStockPrice(e.getStockSymbol(), e.getDate()));
        Assertions.assertEquals(0, stockPriceDao.selectAllStockPrice().size());
        stockPriceDao.insertStockPrice(msft);
        stockPriceDao.insertStockPrice(goog);
        Assertions.assertEquals(2, stockPriceDao.selectAllStockPrice().size());
    }
}
