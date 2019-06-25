#!/usr/bin/env bash

clang -g -O1 -c -emit-llvm -I$GRAALVM_HOME/jre/languages/llvm/include hello.c
cp hello.bc src/main/resources/net/cuprak/graalvm/scripts

mvn package -DskipTests
if [[ ! -f "payara-micro-5.192.jar" ]]; then
    curl https://repo1.maven.org/maven2/fish/payara/extras/payara-micro/5.192/payara-micro-5.192.jar > payara-micro-5.192.jar
fi

java -jar payara-micro-5.192.jar --deploy target/helloworld_multi-1.0-SNAPSHOT.war
