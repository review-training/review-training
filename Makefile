DEFAULT: _package

_clean:
	./mvnw clean

_package: _clean
	./mvnw package

_build: _clean
	./mvnw package -DskipTests -DskipITs

build: _clean
	./mvnw install

run-locally: _build
	./mvnw -f app spring-boot:run

docker-build-image: _build
	docker image build -f app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:latest app/.

docker-run: _build
	docker-compose up --build -d