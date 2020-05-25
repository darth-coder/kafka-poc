package learning.kafkapoc;

import learning.kafkapoc.dao.StockPriceDao;
import learning.kafkapoc.dao.mapper.StockPriceMapper;
import learning.kafkapoc.domain.StockPrice;
import learning.kafkapoc.reader.StockPriceReader;
import learning.kafkapoc.service.StockPriceService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.time.LocalDate;

@SpringBootApplication
public class KafkaPocApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(KafkaPocApplication.class, args);
		StockPriceService stockPriceService = applicationContext.getBean(StockPriceService.class);
		stockPriceService.run();
	}
}
