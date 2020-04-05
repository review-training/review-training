# Review Training
Module created to training some developments skills. The app calculate the average review of restaurants.

## Quality
[![Build Status](https://travis-ci.com/brunokarpo/review-training.svg?branch=master)](https://travis-ci.com/brunokarpo/review-training)
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
To run project locally use the following command:

```shell script
./mvnw -f app compile quarkus:dev
```

You can use `make` client as well:
```shell script
make run-locally
```

## Building the Docker Image

```shell script
docker image build -f app/src/main/docker/Dockerfile.jvm -t brunokarpo/review-app:latest app/.
```

With `make`:

```shell script
make docker-build-image
```


## Running docker container locally

```shell script
docker container run --rm --publish 8080:8080 --name review-app brunokarpo/review-app:latest
```

With `make`:

```shell script
make docker-run
```