DEFAULT: _package

# Maven commands
_clean:
	./mvnw clean

_package: _clean
	./mvnw package

_build: _clean
	./mvnw package -DskipTests -DskipITs

build: _clean
	./mvnw install


# Infra providers
_infra-provide: _infra-stop
	docker container run --rm --name=review-database \
    	 -e POSTGRES_DB=review \
    	 -e POSTGRES_USER=review-app \
    	 -e POSTGRES_PASSWORD=review-app \
    	 -p 5432:5432 \
    	 -d postgres:9.6-alpine

	docker container run --rm --name=review-activemq \
		-e 'ACTIVEMQ_CONFIG_NAME=amqp-srv1' \
		-e 'ACTIVEMQ_CONFIG_DEFAULTACCOUNT=false' \
		-e 'ACTIVEMQ_ADMIN_LOGIN=admin' -e 'ACTIVEMQ_ADMIN_PASSWORD=admin' \
		-e 'ACTIVEMQ_USERS_myproducer=producerpassword' -e 'ACTIVEMQ_GROUPS_writes=myproducer' \
		-e 'ACTIVEMQ_USERS_myconsumer=consumerpassword' -e 'ACTIVEMQ_GROUPS_reads=myconsumer' \
		-e 'ACTIVEMQ_JMX_user1_role=readwrite' -e 'ACTIVEMQ_JMX_user1_password=jmx_password' \
		-e 'ACTIVEMQ_JMX_user2_role=read' -e 'ACTIVEMQ_JMX_user2_password=jmx2_password' \
		-e 'ACTIVEMQ_CONFIG_TOPICS_topic1=REVIEW_RESUME_TOPIC' \
		-e 'ACTIVEMQ_CONFIG_QUEUES_queue1=NEW_REVIEW_QUEUE' \
		-e 'ACTIVEMQ_CONFIG_MINMEMORY=256' -e  'ACTIVEMQ_CONFIG_MAXMEMORY=1024' \
		-e 'ACTIVEMQ_CONFIG_SCHEDULERENABLED=true' \
		-v /data/activemq:/data \
		-v /var/log/activemq:/var/log/activemq \
		-p 8161:8161 \
		-p 61616:61616 \
		-p 61613:61613 \
		-d webcenter/activemq:latest

_infra-stop:
	docker container stop review-database || echo "No infra provided"
	docker container stop review-activemq || echo "No infra provided"

# Dockers
docker-build-image: _build
	docker image build -f spring-app-root/app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:latest spring-app-root/app/.
	docker image build -f quarkus-app-root/quarkus-app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:quarkus quarkus-app-root/quarkus-app/.

docker-run: _build
	docker-compose up --build -d


# Spring
spring-run-locally: _build _infra-provide
	./mvnw -f spring-app-root/app spring-boot:run

spring-docker-run: _build
	docker-compose -f spring-app-root/docker-compose.yml up --build -d

spring-docker-stop:
	docker-compose -f spring-app-root/docker-compose.yml down

# Quarkus
quarkus-run-locally: _build _infra-provide
	./mvnw -f quarkus-app-root/quarkus-app quarkus:dev

quarkus-docker-run: _build
	docker-compose -f quarkus-app-root/docker-compose.yml up --build -d

quarkus-docker-stop:
	docker-compose -f quarkus-app-root/docker-compose.yml down

# Load Tests
load-test: docker-run
	./mvnw -f review-load-test clean gatling:test -Dgatling.simulationClass=simulations.SimulationExecution -DUSERS=120 -DRAMP_DURATION=120 -DDURATION=360