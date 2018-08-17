package test.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@Import(HazelcastConfiguration.class)
public class HazelcastTestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HazelcastTestApplication.class, args);
	}
}