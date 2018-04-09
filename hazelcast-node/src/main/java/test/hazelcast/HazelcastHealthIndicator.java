package test.hazelcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

@Component
public class HazelcastHealthIndicator implements HealthIndicator {

	@Autowired
	HazelcastInstance hazelcast;
	
	@Override
	public Health health() {
		return Health.up().withDetail("nodesCount", hazelcast.getCluster().getMembers().size()).build();
	}
}

