
FROM eclipse-temurin:17-jdk AS builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract
RUN ls -al

FROM eclipse-temurin:17-jre
COPY --from=builder dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
#COPY --from=builder internal-dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]