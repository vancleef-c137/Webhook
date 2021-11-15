FROM openjdk:8
ADD target/timesheet-1.0.jar timesheet.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "timesheet.jar"]