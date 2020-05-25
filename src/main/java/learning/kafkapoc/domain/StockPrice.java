package learning.kafkapoc.domain;

import java.time.LocalDate;

public class StockPrice {

    String stockSymbol;
    LocalDate date;
    Double price;

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
}
