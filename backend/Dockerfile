FROM adoptopenjdk/openjdk11-openj9:alpine AS builder
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew build || return 0 
COPY . .
RUN ./gradlew build -x test 
RUN ls build

FROM adoptopenjdk/openjdk11-openj9:alpine 
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/app-0.0.1-SNAPSHOT.jar bake-sale.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xshareclasses", "-Xquickstart", "-jar","bake-sale.jar"]
