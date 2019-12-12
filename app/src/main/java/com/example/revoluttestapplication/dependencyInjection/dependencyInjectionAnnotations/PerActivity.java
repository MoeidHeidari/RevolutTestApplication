package com.example.revoluttestapplication.dependencyInjection.dependencyInjectionAnnotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author moeidheidari
 * @version 1.0
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity
{
}
