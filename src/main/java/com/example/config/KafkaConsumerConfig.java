package com.example.config;

import com.example.config.Greeting;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/** 
 * ConsumerFactory 객체를 이용해서 각 메시지 종류별로, 메시지를 어디에서 받고 어떠한 방식으로 
 * 처리할 것인지 설정해줌 
 * @author AHN JUN YOUNG
 *
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value="${kafka.bootstrapAddress}")
	private String bootstrapAddress; 
	
	public ConsumerFactory<String, String> consumerFactory(String groupId)
	{
		Map<String, Object> props = new HashMap<>(); 
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress); 
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId); 
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); 
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props);
	}
	
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId) {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>(); 
		factory.setConsumerFactory(consumerFactory(groupId));
		return factory; 
	}
	
	/**
	 * 각 Topic 별로 메시지를 어디서/어떻게 받을지를 설정해주는 메서드 지정 
	 */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory("foo");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> barKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory("bar");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> headersKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory("headers");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> partitionsKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory("partitions");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> filterKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory("filter");
        factory.setRecordFilterStrategy(record -> record.value().contains("world"));
        return factory;
    }	
    
    public ConsumerFactory<String, Greeting> greetingConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "greeting");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Greeting.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Greeting> greetingKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Greeting> factory = new ConcurrentKafkaListenerContainerFactory<String, Greeting>();
        factory.setConsumerFactory(greetingConsumerFactory());
        return factory;
    }    
}
