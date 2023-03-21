FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
EXPOSE 8080

COPY ./build/libs/todo-1.0.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "todo-1.0.jar"]