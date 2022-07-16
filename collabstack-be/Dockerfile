FROM adoptopenjdk:11-jre-hotspot
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir -p /logs
COPY ./app/build/libs/*.jar app.jar
ENTRYPOINT exec java -$PRIVATE_KEY -jar /app.jar
EXPOSE 8080