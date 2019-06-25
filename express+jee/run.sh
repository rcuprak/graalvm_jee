#!/usr/bin/env bash

npm install
mvn package
export lib=bootstrap/target/lib
$GRAALVM_HOME/bin/node --inspect --jvm --vm.Djava.awt.headless=true --vm.cp=bootstrap/target/bootstrap-1.0-SNAPSHOT.jar:$lib/helloworld_api-1.0-SNAPSHOT.jar:$lib/payara-micro-5.192.jar --polyglot server.js
