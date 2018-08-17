package test.hazelcast;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.eureka.one.EurekaOneDiscoveryStrategyFactory;
import com.netflix.discovery.EurekaClient;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class HazelcastConfiguration {

	@Autowired
	private EurekaClient eurekaClient;
	
	@Bean
	Config hazelcastConfig() throws IOException {
		
		log.info("Creating Hazelcast configuration");
		Resource configLocation = new ClassPathResource("hazelcast-eureka.xml");
		Config config = new XmlConfigBuilder(configLocation.getInputStream()).build();
		
		log.info("Eureka instance status: {}", eurekaClient.getApplicationInfoManager().getInfo().getStatus());
		
		EurekaOneDiscoveryStrategyFactory.setEurekaClient(eurekaClient);
		EurekaOneDiscoveryStrategyFactory.setGroupName(config.getGroupConfig().getName());
		
		return config;
	}
}
