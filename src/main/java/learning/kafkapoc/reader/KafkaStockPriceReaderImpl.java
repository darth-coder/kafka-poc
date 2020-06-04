package learning.kafkapoc.reader;

import learning.kafkapoc.domain.StockPrice;
import learning.kafkapoc.writer.StockPriceSerializer;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.*;

@Component
@ConditionalOnProperty(name = "kafka.consumer", havingValue = "true", matchIfMissing = false)
public class KafkaStockPriceReaderImpl implements StockPriceReader {

    private Consumer<String, StockPrice> consumer;

    @Value("${kafka.topic:test}")
    private String topicName;

    @Value("${kafka.consumer.group:testGroup}")
    private String consumerGroup;

    private static int count=0;

    @Override
    public List<StockPrice> read() {
        List<StockPrice> stockPriceList = new ArrayList<>();
        consumer.commitSync();
        ConsumerRecords<String, StockPrice> records = consumer.poll(Duration.ofSeconds(10L));
        records.iterator().forEachRemaining(r -> {
            stockPriceList.add(r.value());
        });
        count+=records.count();
        System.out.println("Consumed records from kafka: " + count);
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e){

        }
        return stockPriceList;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @PostConstruct
    private void postConstruct(){
        Properties props = new Properties();

        //Assign localhost id
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getCanonicalName());

        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StockPriceDeserializer.class.getCanonicalName());

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE.toString());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        consumer = new KafkaConsumer<String, StockPrice>(props);

        consumer.subscribe(Collections.singletonList(topicName));

    }
}
