package learning.kafkapoc;

import learning.kafkapoc.dao.StockPriceDao;
import learning.kafkapoc.dao.mapper.StockPriceMapper;
import learning.kafkapoc.domain.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class KafkaPocApplication implements CommandLineRunner {

	private final StockPriceDao stockPriceDao;
	public KafkaPocApplication(StockPriceDao stockPriceDao) {
		this.stockPriceDao = stockPriceDao;
	}



	public static void main(String[] args) {
		SpringApplication.run(KafkaPocApplication.class, args);
	}

	@Override
	public void run(String... args) {
	}

}
