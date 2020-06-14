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
		-e ARTEMIS_USERNAME=admin \
	  	-e ARTEMIS_PASSWORD=admin \
		-p 8161:8161 \
		-p 61616:61616 \
		-d vromero/activemq-artemis:2.11.0-alpine

_infra-stop:
	docker container stop review-database || echo "No infra provided"
	docker container stop review-activemq || echo "No infra provided"

# Dockers
docker-build-image: _build
	docker image build -f spring-app-root/app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:latest spring-app-root/app/.
	docker image build -f quarkus-app-root/quarkus-app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:quarkus quarkus-app-root/quarkus-app/.

docker-build-image-micronaut: _build
	docker image build -f micronaut-app-root/app/Dockerfile -t brunokarpo/review-app:micronaut-latest micronaut-app-root/app/.

docker-run: _build
	docker-compose up --build -d


# Spring
spring-run-locally: _build _infra-provide
	./mvnw -f spring-app-root/app spring-boot:run

spring-docker-run: _build
	docker-compose -f spring-app-root/docker-compose.yml up --build -d

spring-docker-stop:
	docker-compose -f spring-app-root/docker-compose.yml down

spring-load-test: spring-docker-run
	./mvnw -f review-load-test clean gatling:test -Dgatling.simulationClass=simulations.SimulationExecution -DUSERS=120 -DRAMP_DURATION=120 -DDURATION=360

# Quarkus
quarkus-run-locally: _build _infra-provide
	./mvnw -f quarkus-app-root/quarkus-app quarkus:dev

quarkus-docker-run: _build
	docker-compose -f quarkus-app-root/docker-compose.yml up --build -d

quarkus-docker-stop:
	docker-compose -f quarkus-app-root/docker-compose.yml down

quarkus-load-test: quarkus-docker-run
	./mvnw -f review-load-test clean gatling:test -Dgatling.simulationClass=simulations.SimulationExecution -DUSERS=120 -DRAMP_DURATION=120 -DDURATION=360