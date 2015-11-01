package com.meujornal.infrastructure.shared;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CustomProtocolSocketFactory implements ProtocolSocketFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomProtocolSocketFactory.class);

	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress,
			int localPort) throws IOException, UnknownHostException {
		logger.debug("Initializing socket at {}:{}", host, port);
		return new Socket(host, port);
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress,
			int localPort, HttpConnectionParams params) throws IOException,
			UnknownHostException, ConnectTimeoutException {
		return createSocket(host, port, localAddress, localPort);
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException,
			UnknownHostException {
		return createSocket(host, port, null, 0);
	}

}
