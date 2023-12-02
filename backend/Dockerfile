
# 1st stage, build the app
FROM maven:latest as build
#FROM maven:3.5.4-jdk-9 as build

WORKDIR /java

# Create a first layer to cache the "Maven World" in the local repository.
# Incremental docker builds will always resume after that, unless you update
# the pom

#RUN mvn package -DskipTests

# Do the Maven build!
# Incremental docker builds will resume here when you change sources
ADD src src
ADD pom.xml .
RUN mvn package -DskipTests
RUN dir
RUN echo "done!"

# 2nd stage, build the runtime image
FROM openjdk:17-alpine
WORKDIR /java
# Copy the binary built in the 1st stage
COPY --from=build /java/target/demo-0.0.1-snapshot.jar demo.jar
CMD ["java", "-jar", "demo.jar"]

EXPOSE 8080
# mvn clean install
# docker build -t spring-boot-app:v1 -f Dockerfile .