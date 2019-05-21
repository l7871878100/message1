#!/usr/bin/env bash

git pull

cd web
npm i & npm run build

cd ../

./gradlew build

docker-compose up