#!/usr/bin/env bash

cd web
npm i & npm run build

cd ../

./gradlew build

docker-compose up