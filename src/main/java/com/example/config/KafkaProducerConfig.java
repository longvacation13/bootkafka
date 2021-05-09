package com.example.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.example.*;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

/**
 * 메시지를 발행하는 Producer에 관한 설정 
 * @author AHN JUN YOUNG
 *
 */
@Configuration
public class KafkaProducerConfig {

	@Value(value="${kafka.bootstrapAddress}")
	private String bootstrapAddress; 
	
	/**
	 * producerFactory 객체의 역할 
	 * - 각 메시지 종류별로, 메시지를 어디에 보내고, 어떠한 방식으로 처리할 것인지 설정 
	 * - 실제 메시지는 kafkaTemplate이라는 객체에 담아서 보냄 
	 * - (일종의 편지봉투)
	 * @return
	 */
	@Bean
	public ProducerFactory<String, String> producerFactory(){
		Map<String, Object> configProps = new HashMap<>(); 
		configProps.put(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
				bootstrapAddress
		 );
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
				StringSerializer.class
		);
		configProps.put(
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
				StringSerializer.class
		 );
		return new DefaultKafkaProducerFactory<>(configProps); 
	}
	
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
	   return new KafkaTemplate<String, String>(producerFactory());
	}

	public ProducerFactory<String, Greeting> greetingProducerFactory() {
	   Map<String, Object> configProps = new HashMap<>();
	   configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
	   configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	   configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	   return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Greeting> greetingKafkaTemplate() {
	    return new KafkaTemplate<>(greetingProducerFactory());
	}
	
}
