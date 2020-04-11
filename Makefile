DEFAULT: _package

_clean:
	./mvnw clean

_package: _clean
	./mvnw package

_build: _clean
	./mvnw package -DskipTests -DskipITs

run-locally: _build
	./mvnw -f app spring-boot:run

docker-build-image: _build
	docker image build -f app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:latest app/.

docker-run: docker-build-image
	docker-compose up --build -d

k8s-deploy:
	kubectl apply -f k8s/namespace.yml
	kubectl create secret generic pg-password --from-literal=POSTGRES_PASSWORD='my-random-password' --namespace="review-application"
	kubectl apply -f k8s/postgresql/
	kubectl apply -f k8s/app/