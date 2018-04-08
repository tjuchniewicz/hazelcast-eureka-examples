package test.hazelcast;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class HazelcastTestApplication {
	
	@Autowired
	HazelcastInstance hazelcast;
	
	public static void main(String[] args) {
		SpringApplication.run(HazelcastTestApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		IMap<String, Integer> map = hazelcast.getMap("test");
		if (!map.containsKey("counter")) {
			map.put("counter", 1);
		} else {
			map.put("counter", map.get("counter") + 1);
		}
		log.info("==== Distributed counter: {}", map.get("counter"));
		
	}
}