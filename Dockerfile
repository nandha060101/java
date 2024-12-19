FROM eclipse-temurin:11.0.14_9-jre-alpine
ENV TZ="Asia/Kolkata"
VOLUME /tmp
EXPOSE 80
ARG EXTRACTED=./target/extracted
WORKDIR "/home"
COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./
COPY dd-java-agent.jar /opt
ENV JAVA_TOOL_OPTIONS "-javaagent:/opt/dd-java-agent.jar -XX:FlightRecorderOptions=stackdepth=256 -XX:MinRAMPercentage=50.0 -XX:MaxRAMPercentage=100.0 -XX:TieredStopAtLevel=1 -Dlog4j.formatMsgNoLookups=true -XX:+UnlockExperimentalVMOptions -Dspring.backgroundpreinitializer.ignore=true"
CMD ["java","org.springframework.boot.loader.JarLauncher"]
