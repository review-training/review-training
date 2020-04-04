_clean:
	./mvnw clean

_package: _clean
	./mvnw package

run-locally:
	./mvnw -f app compile quarkus:dev

docker-build-image:
	docker image build -f app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:latest app/.

docker-run: docker-build-image
	docker container run --rm --publish 8080:8080 --name review-app brunokarpo/review-app:latest