FROM adoptopenjdk/openjdk11:alpine-slim
RUN apk add tzdata
RUN apk add netcat-openbsd
RUN cp /usr/share/zoneinfo/Asia/Bangkok /etc/localtime
WORKDIR /app/
COPY ./build/libs/rookie-workshop-spring-0.0.1-SNAPSHOT.jar .
CMD ["java", "-Xmx256M","-jar", "rookie-workshop-spring-0.0.1-SNAPSHOT.jar"]