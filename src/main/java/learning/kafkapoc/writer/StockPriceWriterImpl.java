package learning.kafkapoc.writer;

import learning.kafkapoc.dao.StockPriceDao;
import learning.kafkapoc.domain.StockPrice;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

@Component
public class StockPriceWriterImpl implements StockPriceWriter {

    @Value("${writeStockPriceToKafka}")
    private Boolean writeStockPriceToKafka;

    private static Logger logger = LogManager.getLogger(StockPriceWriterImpl.class);

    @Autowired
    StockPriceDao stockPriceDao;

    private final String topicName = "test";

    KafkaProducer<String, StockPrice> kafkaProducer;

    @PostConstruct
    private void postConstruct(){
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        props.put("retries", 0);

        props.put("key.serializer",
                StringSerializer.class.getCanonicalName());

        props.put("value.serializer",
                StockPriceSerializer.class.getCanonicalName());

        kafkaProducer = new KafkaProducer<String, StockPrice>(props);

    }

    @Override
    public void writeStockPrice(StockPrice stockPrice) {
        writeToDB(stockPrice);
        pushToKafka(stockPrice);
    }

    private void writeToDB(StockPrice stockPrice) {
        stockPriceDao.insertStockPrice(stockPrice);
    }

    private void pushToKafka(StockPrice stockPrice) {
        if(!writeStockPriceToKafka)
            return;
        ProducerRecord<String, StockPrice> record = new ProducerRecord<>(topicName, stockPrice);
        kafkaProducer.send(record, onCompletion);
        kafkaProducer.flush();
    }

    private Callback onCompletion = (a,b) -> {
        //logger.info("Partition " + a.partition() + " and offset {}" +  a.offset());
    };
}
