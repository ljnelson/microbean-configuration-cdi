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
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;

@Documented
@Repeatable(ConfigurationCoordinates.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE })
public @interface ConfigurationCoordinate {

  String name();
  
  String value() default "";

  public static final class Literal extends AnnotationLiteral<ConfigurationCoordinate> implements ConfigurationCoordinate {

    public static final ConfigurationCoordinate INSTANCE = new Literal("", "");
    
    private static final long serialVersionUID = 1L;

    private final String name;
    
    private final String value;

    public final String name() {
      return this.name;
    }
    
    public final String value() {
      return this.value;
    }

    public Literal(final String name, final String value) {
      super();
      this.name = name == null ? "" : name;
      this.value = value == null ? "" : value;
    }

  }
  
}