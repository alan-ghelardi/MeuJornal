package com.meujornal.infrastructure.shared;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.ExecutorService;

import javax.inject.Qualifier;

/**
 * Qualifier para possibilitar a injeção de um thread pool customizado, evitando
 * uma ambiguidade na resolução de instâncias de {@link ExecutorService} como
 * dependências gerenciadas pelo CDI.
 * 
 * @author Alan
 *
 */
@Qualifier
@Retention(RUNTIME)
@Target({ CONSTRUCTOR, FIELD, METHOD, PARAMETER, TYPE })
public @interface CustomThreadPool {

}
