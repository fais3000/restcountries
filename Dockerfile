FROM adoptopenjdk/openjdk13-openj9:jdk-13.0.2_8_openj9-0.18.0-alpine-slim
COPY target/restcountries-*.jar restcountries.jar
EXPOSE 5055
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-jar", "restcountries.jar"]
