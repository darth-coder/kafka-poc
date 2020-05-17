package learning.kafkapoc.dao;

import learning.kafkapoc.dao.mapper.StockPriceMapper;
import learning.kafkapoc.domain.StockPrice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Repository
public class StockPriceDaoImpl implements StockPriceDao {


    private final StockPriceMapper mapper;

    StockPriceDaoImpl(StockPriceMapper mapper) {
     this.mapper = mapper;
    }

    @Override
    public void insertStockPrice(StockPrice stockPrice) {
        mapper.insertData(stockPrice);
    }

    @Override
    public StockPrice selectStockPrice(String stockSymbol, LocalDate date) {
        return mapper.getBySymbolDate(stockSymbol, date);
    }

    @Override
    public List<StockPrice> selectAllStockPrice() {
        return mapper.getAllData();
    }

    @Override
    public void deleteStockPrice(String stockSymbol, LocalDate date) {
        mapper.delete(stockSymbol, date);
    }
}
