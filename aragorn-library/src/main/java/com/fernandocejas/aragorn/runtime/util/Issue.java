package com.fernandocejas.aragorn.runtime.util;

/**
 * Used to indicates that the annotated member has a relation with an issue.
 * A string value can be used for more information, such as a url or a description.
 */
public @interface Issue {
  String value() default "";
}
