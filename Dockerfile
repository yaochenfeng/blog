FROM  node as NODE
WORKDIR /usr/src/app
RUN git clone https://github.com/yaochenfeng/weblog.git /usr/src/app &&\
    npm install && npm run build

FROM  openjdk:8 as BUILD
WORKDIR /usr/src/app
COPY . .
COPY --from=NODE /usr/src/app/dist/ src/main/resources/public/
RUN ./gradlew bootJar

FROM java:8-jre-alpine

#add timezone and default it to Shanghai
RUN echo http://mirrors.aliyun.com/alpine/v3.6/main > /etc/apk/repositories;\
    apk --update add --no-cache tzdata curl
ENV TZ=Asia/Shanghai \
    JAVA_OPTS="" \
    SPRING_PROFILES_ACTIVE="prod" \
    APP_PORT=8080 \
    SPRING_APPLICATION_JSON='{"server.port":"$APP_PORT"}' \
    HEALTH_URL="localhost:8080/actuator/health"

COPY --from=BUILD /usr/src/app/build/libs/*.jar app.jar
EXPOSE $APP_PORT

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

HEALTHCHECK --interval=10s --timeout=2s --retries=12 CMD curl -f $HEALTH_URL || exit 1
