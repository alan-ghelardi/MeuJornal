package com.meujornal.infrastructure.shared;

import javax.enterprise.event.Observes;

import org.apache.commons.httpclient.protocol.Protocol;

import br.com.caelum.vraptor.events.VRaptorInitialized;

public class ProtocolSocketFactoryConfigurator {

	public void registerProtocolSocketFactory(@Observes VRaptorInitialized event) {
		Protocol.registerProtocol("http", new Protocol("http",
				new CustomProtocolSocketFactory(), 80));
	}

}
