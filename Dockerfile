FROM  node:alpine as NODE
WORKDIR /usr/src/app
COPY appfront/package.json .
RUN npm install
COPY appfront .
RUN  npm run build

FROM  maven:alpine as BUILD
WORKDIR /usr/src/app
COPY . .
COPY --from=NODE /usr/src/app/dist/ src/main/resources/public/
RUN mvn clean package -nsu -Dmaven.test.skip=true


FROM java:8-jre-alpine

#add timezone and default it to Shanghai
RUN echo http://mirrors.aliyun.com/alpine/v3.6/main > /etc/apk/repositories;\
    apk --update add --no-cache tzdata curl
ENV TZ=Asia/Shanghai \
    JAVA_OPTS="" \
    SPRING_PROFILES_ACTIVE="prod" \
    APP_PORT=8080 \
    SPRING_APPLICATION_JSON='{"server.port":$APP_PORT}' \
    HEALTH_URL="localhost:8080/actuator/health"

COPY --from=BUILD /usr/src/app/target/*.jar app.jar
EXPOSE $APP_PORT

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

HEALTHCHECK --interval=10s CMD curl -f $HEALTH_URL || exit 1
