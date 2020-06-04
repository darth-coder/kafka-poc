package learning.kafkapoc.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class StockPrice {

    @CsvBindByName(required = true, column = "stockSymbol")
    String stockSymbol;

    @CsvBindByName(required = true, column = "date")
    @CsvDate("yyyy-MM-dd")
    LocalDate date;

    @CsvBindByName(required = true, column = "price")
    Double price;

    public StockPrice() {

    }

    public StockPrice(String stockSymbol, LocalDate date, Double price) {
        this.stockSymbol = stockSymbol;
        this.date = date;
        this.price = price;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public StockPrice(StockPrice stockPrice) {
        this.stockSymbol = stockPrice.stockSymbol;
        this.date = stockPrice.date;
        if(stockPrice.price != null) {
            this.price = Double.valueOf(stockPrice.price);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPrice that = (StockPrice) o;
        return stockSymbol.equals(that.stockSymbol) &&
                date.equals(that.date) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockSymbol, date, price);
    }
}
