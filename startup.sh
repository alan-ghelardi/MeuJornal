#!/bin/sh
mvn clean install&&chmod +x target/wildfly-8.2.0.Final/bin/standalone.sh&&target/wildfly-8.2.0.Final/bin/standalone.sh -Djboss.http.port=8080 -b 0.0.0.0
