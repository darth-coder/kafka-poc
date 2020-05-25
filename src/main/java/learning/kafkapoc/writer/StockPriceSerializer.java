package learning.kafkapoc.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class StockPriceSerializer implements Serializer {
        @Override
        public byte[] serialize(String topic, Object data) {
            byte[] retVal = null;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
            try {
                retVal = objectMapper.writeValueAsString(data).getBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return retVal;
        }

        @Override
        public void configure(Map configs, boolean isKey) {

        }

        @Override
        public byte[] serialize(String topic, Headers headers, Object data) {
            return serialize(topic, data);
        }

        @Override
        public void close() {

        }
    }
