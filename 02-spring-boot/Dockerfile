FROM adoptopenjdk/openjdk11:centos AS builder
WORKDIR /opt/app
COPY . /opt/app/
RUN make _build

FROM openjdk:11
EXPOSE 8080
COPY --from=builder /opt/app/build/libs/*.jar /hello-world.jar
CMD ["java", "-jar", "/hello-world.jar"]