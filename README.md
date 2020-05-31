# Review Training
Module created to training some developments skills. The app calculate the average review of restaurants.

## Quality
[![Build Status](https://travis-ci.com/review-training/review-training.svg?branch=master)](https://travis-ci.com/review-training/review-training)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=brunokarpo_review-training&metric=alert_status)](https://sonarcloud.io/dashboard?id=brunokarpo_review-training)

## Building project
To build the project you can use embedded Maven client of project doing the following command:

```shell script
./mvnw clean install
```

If you are using a Linux based system, you can use our helper command `make`.

```shell script
make _build
```

## Running project locally
This project was build with Spring Boot and Quarkus and you can choose with one you like to run.

### With Spring Boot

```shell script
./mvnw -f spring-app-root/app spring-boot:run
```

You can use `make` client as well:
```shell script
make spring-run-locally
```

### With Quarkus
```shell script
./mvnw -f quarkus-app-root/quarkus-app quarkus:dev
```

You can use `make` client as well:
```shell script
make quarkus-run-locally
```


## Running docker container locally
Like locally execution, you can run with Docker with Spring Boot or Quarkus using Docker Compose description.

### With Spring Boot

```shell script
docker-compose -f spring-app-root/docker-compose.yml up --build -d
```

You can use `make` client as well:
```shell script
make spring-docker-run
```

### With Quarkus
```shell script
docker-compose -f quarkus-app-root/docker-compose.yml up --build -d
```

You can use `make` client as well:
```shell script
make quarkus-docker-run
```