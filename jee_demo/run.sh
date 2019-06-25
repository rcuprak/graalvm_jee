#!/usr/bin/env bash

mvn package

mvn package -DskipTests
if [[ ! -f "payara-micro-5.192.jar" ]]; then
    curl https://repo1.maven.org/maven2/fish/payara/extras/payara-micro/5.192/payara-micro-5.192.jar > payara-micro-5.192.jar
fi

java -jar payara-micro-5.192.jar --deploy target/jee_demo-1.0-SNAPSHOT.war