package test.hazelcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import com.hazelcast.core.IMap;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HazelcastExampleService {

	@Autowired
	HazelcastInstance hazelcast;
	
	@Scheduled(fixedRate = 5000)
	public void updateDistributedCounter() {
		
	    IMap<String, Integer> map = hazelcast.getMap("test");

	    ILock lock = hazelcast.getLock("example");
		lock.lock();
		
		if (!map.containsKey("counter")) {
			map.put("counter", 1);
		} else {
			map.put("counter", map.get("counter") + 1);
		}
		
		lock.unlock();
		log.info("==== Distributed counter: {}", map.get("counter"));
	}
}
