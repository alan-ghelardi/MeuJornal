#!/bin/sh

local_server_dir="./local-server"

if [ ! -d "$local_server_dir" ]; then
	echo "The local server directory does not exist. Creating it..."
	mkdir "$local_server_dir"
	echo "Running Maven. The Wildfly server will be downloaded and installed automatically..."
	mvn clean install -P install_wildfly
else
	echo "The Wildfly server already exists. Running Maven..."
	mvn clean install
fi
