FROM adoptopenjdk/openjdk11:alpine-slim
WORKDIR /app/
COPY ./build/libs/rookie-workshop-spring-0.0.1-SNAPSHOT.jar .
CMD ["java", "-Xmx256M","-jar", "rookie-workshop-spring-0.0.1-SNAPSHOT.jar","-Duser.timezone=Asia/Bangkok"]