package com.example.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;


/**
 * Kafka 토픽 설정 class
 * @author AHN JUN YOUNG
 *
 */
@Configuration 
public class KafkaTopicConfig {

	/**
	 * bootstrapAddress 설정 : 
	 * */
	@Value(value="${kafka.bootstrapAddress}")
	private String bootstrapAddress; 
	
	@Value(value="${message.topic.name}")
	private String topicName; 
	
	@Value(value="${partitioned.topic.name}")
	private String partitionedTopicName; 
	
	@Value(value="${filtered.topic.name}")
	private String filteredTopicName; 
	
	@Value(value="${greeting.topic.name}")
	private String greetingTopicName; 
	
	@Bean 
	public KafkaAdmin kafkaAdmin()
	{
		Map<String, Object> configs = new HashMap<>(); 
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);		
		return new KafkaAdmin(configs);
	}
	
	/** 토픽 생성 : 토픽을 생성하고 Bean 으로 등록해주면 자동으로 토픽을 생성해서 주입 */ 
    @Bean
    public NewTopic topic1() {
        return new NewTopic(topicName,1,(short)1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic(partitionedTopicName, 6, (short) 1);
    }

    @Bean
    public NewTopic topic3() {
        return new NewTopic(filteredTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic topic4() {
        return new NewTopic(greetingTopicName, 1, (short) 1);
    }
	
}