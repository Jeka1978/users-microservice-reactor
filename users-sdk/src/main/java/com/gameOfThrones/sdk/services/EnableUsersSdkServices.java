package com.gameOfThrones.sdk.services;/**
 * Created by Evegeny on 25/05/2017.
 */

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Import(Conf.class)
public @interface EnableUsersSdkServices {
}
