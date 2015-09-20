package com.fernandocejas.aragorn.runtime.util;

/**
 * Used to indicates that the annotated member is in Progress.
 * A string value can be used for more information, such as a url or a description.
 */
public @interface InProgress {
  String value() default "";
}
