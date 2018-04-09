This is example how we use Hazelcast Eureka Discovery when Hazelcast is embedded in a web application and we share one Eureka client between web and Hazelcast.

In this example we use https://github.com/hazelcast/hazelcast-eureka fork: https://github.com/ebjwc/eurekast-one/tree/gh-6b.

To start this example:

```
cd eureka
mvn spring-boot:run

cd hazelcast-node
mvn spring-boot:run (repeat at least two times)
```
