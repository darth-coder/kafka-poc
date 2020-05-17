package learning.kafkapoc.dao.mapper;

import learning.kafkapoc.domain.StockPrice;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface StockPriceMapper {

    @Results(id = "stockPrice",value = {
            @Result(property = "stockSymbol", column = "stock_symbol", id=true),
            @Result(property = "date", column = "date"),
            @Result(property = "price", column = "price")
    })
    @Select("SELECT * FROM stock_price WHERE stock_symbol=#{stockSymbol} AND date=#{date}")
    StockPrice getBySymbolDate(@Param("stockSymbol") String stockSymbol, @Param("date") LocalDate date);


    @ResultMap("stockPrice")
    @Select("SELECT * FROM stock_price")
    List<StockPrice> getAllData();

    @Insert("INSERT INTO stock_price(stock_symbol, date, price) VALUES (#{stockSymbol}, #{date}, #{price})")
    void insertData(StockPrice stockPrice);

    @Delete("DELETE FROM stock_price WHERE stock_symbol=#{stockSymbol} AND date=#{date}")
    void delete(@Param("stockSymbol") String stockSymbol, @Param("date") LocalDate date);

}
