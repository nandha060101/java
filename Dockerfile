FROM eclipse-temurin:11-jre-alpine
ENV TZ="Asia/Kolkata"
VOLUME /tmp
EXPOSE 80
HEALTHCHECK --interval=30s --retries=3 --start-period=50s  --timeout=10s   CMD [ "wget", "-nv", "-t1", "--spider", "localhost/actuator/health" ]
ARG EXTRACTED=./target/extracted
WORKDIR "/home"
COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./
RUN wget -O /opt/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
ENV JAVA_TOOL_OPTIONS "-javaagent:/opt/dd-java-agent.jar -XX:FlightRecorderOptions=stackdepth=256 -XX:MinRAMPercentage=50.0 -XX:MaxRAMPercentage=100.0 -XX:TieredStopAtLevel=1 -Dlog4j.formatMsgNoLookups=true -XX:+UnlockExperimentalVMOptions -Dspring.backgroundpreinitializer.ignore=true"
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
