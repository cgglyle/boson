FROM amazoncorretto:18
MAINTAINER cgglyle<cgglyle@outlook.com>

# 调整容器时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \&& echo 'Asia/Shanghai' >/etc/timezone

VOLUME /tmp

ADD application/target/application-0.0.1-SNAPSHOT.jar application-0.0.1-SNAPSHOT.jar
ADD common/target/common-0.0.1-SNAPSHOT.jar common-0.0.1-SNAPSHOT.jar
ADD permissions/target/permissions-0.0.1-SNAPSHOT.jar permissions-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/application-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
