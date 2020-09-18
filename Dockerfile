FROM adoptopenjdk/openjdk11:alpine-slim as builder
WORKDIR /app/
COPY ./build/libs/rookie-workshop-spring-0.0.1-SNAPSHOT.jar .

FROM alpine:3.12.0
RUN apk add tzdata
ENV TZ=Asia/Bangkok
COPY --from=builder app .
CMD ["java", "-Xmx256M","-jar", "rookie-workshop-spring-0.0.1-SNAPSHOT.jar"]