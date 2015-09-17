#!/bin/sh
sed -i -e 's,<servlet-container name="default">,<servlet-container name="default" use-listener-encoding="true" default-encoding="UTF-8">,g' target/wildfly-8.2.0.Final/standalone/configuration/standalone.xml
sed -i -e 's,<subsystem xmlns="urn:jboss:domain:logging:2.0">,<subsystem xmlns="urn:jboss:domain:logging:2.0"><add-logging-api-dependencies value="false"/><use-deployment-logging-config value="false"/>,g' target/wildfly-8.2.0.Final/standalone/configuration/standalone.xml
chmod +x target/wildfly-8.2.0.Final/bin/standalone.sh
target/wildfly-8.2.0.Final/bin/standalone.sh -Djboss.http.port=8080 -b 0.0.0.0
