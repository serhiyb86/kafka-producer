# kafka-producer

## Usage
`./gradlew clean build
`
## Running the app

`
docker-compose up
`
## Running the app with arguments
`java -jar build/libs/kafka-producer-1.0-SNAPSHOT-all.jar topic-name 10 5 `

docker exec --workdir /opt/kafka/bin/ -it broker sh

./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic my-topic

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic --from-beginning




curl http://localhost:8081/subjects 