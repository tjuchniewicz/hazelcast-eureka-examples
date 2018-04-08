package test.hazelcast;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastInstanceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.eureka.one.EurekaOneDiscoveryStrategyFactory;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.EurekaClient;

@Configuration
public class HazelcastConfiguration {

	@Autowired
	EurekaClient eurekaClient;
	
	@Bean
	HazelcastInstance hazelcast() throws IOException {
		
		EurekaOneDiscoveryStrategyFactory.setEurekaClient(eurekaClient);
		
		Resource configLocation = new ClassPathResource("hazelcast.xml");
		HazelcastInstance instance = new HazelcastInstanceFactory(configLocation).getHazelcastInstance();
		int port = instance.getCluster().getLocalMember().getAddress().getPort();
		String host = instance.getCluster().getLocalMember().getAddress().getHost();
		
		// provide Hazelcast info in Eureka metadata
		Map<String, String> map = ApplicationInfoManager.getInstance().getInfo().getMetadata();
		map.put("hazelcast.port", Integer.toString(port));
		map.put("hazelcast.host", host);
		
		return instance;
	}
}
