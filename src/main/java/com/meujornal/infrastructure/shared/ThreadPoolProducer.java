package com.meujornal.infrastructure.shared;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe responsável por criar um pool de threads para a aplicação.
 * 
 * @author Alan Ghelardi
 *
 */
public class ThreadPoolProducer {

	private static final Logger logger = LoggerFactory
			.getLogger(ThreadPoolProducer.class);

	@ApplicationScoped
	@Produces
	@CustomThreadPool
	public ExecutorService createThreadPool() {
		logger.debug("Creating new thread pool for the application");
		return Executors.newCachedThreadPool();
	}

	public void release(@Disposes @CustomThreadPool ExecutorService threadPool) {
		logger.debug("Releasing thread pool");
		threadPool.shutdown();
	}

}
