package com.example.revoluttestapplication.dependencyInjection.dependencyInjectionAnnotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author moeidheidari
 * @version 1.0
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}