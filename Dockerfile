FROM openjdk:15.0.2-oraclelinux7
ADD src/main/java/GAMEWN.jar GAMEWN.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "GAMEWN.jar"]

