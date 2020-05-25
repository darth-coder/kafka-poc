package learning.kafkapoc.reader;

import com.opencsv.bean.CsvToBeanBuilder;
import learning.kafkapoc.domain.StockPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class FeedStockPriceReaderImpl implements StockPriceReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedStockPriceReaderImpl.class);

    @Value("${feed.filename}")
    private String fileName;

    @Override
    public List<StockPrice> read() {
        try (Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(fileName).toURI()))) {
            List<StockPrice> stockPrices = new CsvToBeanBuilder<StockPrice>(reader)
                    .withType(StockPrice.class)
                    .build().parse();
            LOGGER.info("FeedStockPriceReader read {} prices from file : {}", stockPrices.size(), fileName);
            return stockPrices;
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect to feeds. Due to Exception :" + e.getMessage());
        }
    }
}
