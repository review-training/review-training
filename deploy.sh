#!/bin/bash

git tag -a $TAG -m "Creating tag version $TAG"
git push origin $TAG
echo "$DOCKER_PASSWORD" | docker login --username "$DOCKER_USERNAME" --password-stdin
docker push brunokarpo/review-app:latest
docker push brunokarpo/review-app:$TAG