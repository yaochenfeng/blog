FROM  node:alpine as NODE
WORKDIR /usr/src/app
COPY appfront/package.json .
RUN npm install
COPY appfront .
RUN  npm run build

FROM  maven:alpine as BUILD
WORKDIR /usr/src/app
COPY . .
COPY --from=NODE /usr/src/app/dist/ appfront/dist
RUN mvn clean package -nsu -Dmaven.test.skip=true


FROM java:8-jre-alpine

#add timezone and default it to Shanghai
RUN echo http://mirrors.aliyun.com/alpine/v3.6/main > /etc/apk/repositories;\
    apk --update add --no-cache tzdata
ENV TZ=Asia/Shanghai \
    JAVA_OPTS="" \
    SPRING_PROFILES_ACTIVE="prod" \
    APP_PORT=8080 \
    SPRING_APPLICATION_JSON='{"server.port":$APP_PORT}'
VOLUME /tmp
COPY --from=BUILD /usr/src/app/target/*.jar app.jar
EXPOSE $APP_PORT

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar

#HEALTHCHECK --interval=10s CMD curl -f http://localhost:$APP_PORT/health || exit 1