/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2017 MicroBean.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.microbean.configuration.cdi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import javax.enterprise.util.AnnotationLiteral;
import javax.enterprise.util.Nonbinding;

import javax.inject.Qualifier;

@Documented
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE })
public @interface ConfigurationValue {

  public static final String NULL = "\0";
  
  @Nonbinding
  String value() default "";

  @Nonbinding
  String defaultValue() default NULL;

  public static final class Literal extends AnnotationLiteral<ConfigurationValue> implements ConfigurationValue {

    public static final ConfigurationValue INSTANCE = of("");
    
    private static final long serialVersionUID = 1L;
    
    private final String value;

    private final String defaultValue;

    private Literal(final String value, final String defaultValue) {
      super();
      this.value = value == null ? "" : value;
      this.defaultValue = defaultValue == null ? NULL : defaultValue;
    }

    @Override
    public final String defaultValue() {
      return this.defaultValue;
    }
    
    @Override
    public final String value() {
      return this.value;
    }

    public static final Literal of(final String value) {
      return new Literal(value, NULL);
    }

    public static final Literal of(final String value, final String defaultValue) {
      return new Literal(value, defaultValue);
    }
    
  }
  
}
