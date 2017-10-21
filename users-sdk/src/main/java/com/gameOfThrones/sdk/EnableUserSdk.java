package com.gameOfThrones.sdk;/**
 * Created by Evegeny on 03/06/2017.
 */

import com.gameOfThrones.sdk.services.Conf;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Import(Conf.class)
public @interface EnableUserSdk {
}
