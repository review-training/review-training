DEFAULT: _package

_clean:
	./mvnw clean

_package: _clean
	./mvnw package

_build: _clean
	./mvnw package -DskipTests -DskipITs

build: _clean
	./mvnw install

_infra-provide: _infra-stop
	docker run --rm --name=review-database\
    	 -e POSTGRES_DB=review\
    	 -e POSTGRES_USER=review-app\
    	 -e POSTGRES_PASSWORD=review-app\
    	 -p 5432:5432\
    	 -d postgres:9.6-alpine

_infra-stop:
	docker container stop review-database || echo "No infra provided"

run-locally: _build _infra-provide
	./mvnw -f app spring-boot:run

docker-build-image: _build
	docker image build -f app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:latest app/.

docker-run: _build
	docker-compose up --build -d