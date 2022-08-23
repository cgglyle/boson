FROM amazoncorretto:18
MAINTAINER cgglyle<cgglyle@outlook.com>

# 调整容器时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \&& echo 'Asia/Shanghai' >/etc/timezone

VOLUME /tmp

ADD application/target/application*.jar app.jar

ENTRYPOINT ["java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

EXPOSE 8080
