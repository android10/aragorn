package com.fernandocejas.aragorn.runtime;

import com.fernandocejas.aragorn.runtime.util.VisibleForTesting;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Set;

@SuppressWarnings("unused")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AragornAnnotationProcessor extends AbstractProcessor {
    private final AnnotationHandler.Factory annotationHandlerFactory;

    public AragornAnnotationProcessor() {
        super();
        this.annotationHandlerFactory = new AnnotationHandler.Factory();
    }

    @VisibleForTesting
    AragornAnnotationProcessor(AnnotationHandler.Factory annotationHandlerFactory) {
        this.annotationHandlerFactory = annotationHandlerFactory;
    }

    @Override public Set<String> getSupportedAnnotationTypes() {
        return this.annotationHandlerFactory.getSupportedAnnotationTypes();
    }

    @Override public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnvironment) {
        for (Class<? extends Annotation> annotation : this.annotationHandlerFactory.getSupportedAnnotationClasses()) {
            for (Element element : roundEnvironment.getElementsAnnotatedWith(annotation)) {
                AnnotationHandler annotationHandler = this.annotationHandlerFactory.create(annotation);
                if (annotationHandler != null) {
                    annotationHandler.process(processingEnv, element);
                }
            }
        }
        return true;
    }
}
