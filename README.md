# spring-boot-with-kafka
Demo project for Spring Boot implementing kafka service.

Pre-requisites:
* Install kafka on your local machine or connect to a remote kafka queue.
* Install kafka on mac: <br />
brew install kafka <br />
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties

Start up the application <br/>
curl -X POST -F 'message=first-message' http://localhost:9000/kafka/publish
