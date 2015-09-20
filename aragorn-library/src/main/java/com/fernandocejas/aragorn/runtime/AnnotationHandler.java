package com.fernandocejas.aragorn.runtime;

import com.fernandocejas.aragorn.annotation.RxLogObservable;
import com.fernandocejas.aragorn.annotation.RxLogSubscriber;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

abstract class AnnotationHandler {
    abstract void process(ProcessingEnvironment processingEnvironment, Element element);

    void printMessage(ProcessingEnvironment processingEnvironment, String message) {
        processingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
    }

    static class Factory {
        @SuppressWarnings("unchecked")
        private static final List<? extends Class<? extends Annotation>> SUPPORTED_ANNOTATIONS =
                Arrays.asList(RxLogObservable.class, RxLogSubscriber.class);

        Factory() {}

        AnnotationHandler create(Class<? extends Annotation> annotationClass) {
            AnnotationHandler annotationHandler = null;
            if (annotationClass.equals(RxLogObservable.class)) {
                annotationHandler = new RxLogObservableHandler();
            } else if (annotationClass.equals(RxLogSubscriber.class)) {
                annotationHandler = new RxLogSubscriberHandler();
            }
            return annotationHandler;
        }

        Set<String> getSupportedAnnotationTypes() {
            Set<String> supportedTypes = new LinkedHashSet<>();
            for (Class<? extends Annotation> annotationType : SUPPORTED_ANNOTATIONS) {
                supportedTypes.add(annotationType.getCanonicalName());
            }
            return supportedTypes;
        }

        List<? extends Class<? extends Annotation>> getSupportedAnnotationClasses() {
            return SUPPORTED_ANNOTATIONS;
        }
    }
}
