package com.fernandocejas.aragorn.runtime.util;

import static com.fernandocejas.aragorn.runtime.util.Todo.Severity.TRIVIAL;

/**
 * Used to indicates that the annotated member has a pending TODO task.
 * A string value can be used for more information, such as a url or a description.
 * A {@link Todo.Severity} value can be used to describe the TODO purpose.
 */
public @interface Todo {
    String value() default "";

    Severity severity() default TRIVIAL;

    /**
     * Severity description.
     */
    enum Severity {
        TRIVIAL, IMPORTANT, CRITICAL, DOCUMENTATION
    }
}
