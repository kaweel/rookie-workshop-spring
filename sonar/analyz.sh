#!/bin/bash

./gradlew test sonarqube \
  -Dsonar.projectKey=rookie-workshop-spring \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=e1cb310531be6da130d190106513a7592c520fd5