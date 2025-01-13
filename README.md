# pastry-notification-service
Microservice responsible for internal notification of orders from a pastry shop. This is an academic project for the Software Engineering MBA

no diretório src/main/resources/ executar o comando para iniciar os containers:
docker-compose up -d

executar o comando para garantir que os containers subiram com sucesso:
docker ps

entrar no container do Kafka para conseguir produzir e consumir eventos:
docker exec -it resources-kafka-1

dentro do terminal do container, usar o comando para criar o tópico:
kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic success-notification

dentro do terminal do container, usar o comando para produzir eventos
kafka-console-producer --broker-list localhost:9092 --topic success-notification

dentro do terminal do container, usar o comando para consumir eventos
kafka-console-consumer --bootstrap-server localhost:9092 --topic success-notification --from-beginning

para acessar a interface do kafdrop e visualizar as informações do Kafka, acessar:
http://localhost:19000/