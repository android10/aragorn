package com.fernandocejas.aragorn.runtime;

import com.fernandocejas.aragorn.annotation.RxLogObservable;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

class RxLogObservableHandler extends AnnotationHandler {
    @Override public void process(ProcessingEnvironment processingEnvironment, Element element) {
        if (isValidElement(element)) {
            //TODO: Do something with the annotation here.
            //TODO: Like generate new files, etc
            this.printMessage(processingEnvironment, this.buildInformationMessage(element));
        } else {
            throw new RuntimeException(this.buildErrorMessage(element));
        }
    }

    private boolean isValidElement(Element element) {
        boolean isValid = false;
        Set<Modifier> modifiers = element.getModifiers();

        if (modifiers != null) {
            isValid = !(modifiers.contains(Modifier.FINAL) || modifiers.contains(Modifier.STATIC) ||
                    modifiers.contains(Modifier.ABSTRACT));
        }

        return isValid;
    }

    private String buildInformationMessage(Element element) {
        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder.append(RxLogObservable.class.getSimpleName());
        messageBuilder.append(" Annotation found in ");
        messageBuilder.append(element.getSimpleName().toString());
        messageBuilder.append(" ");
        messageBuilder.append(element.getKind().name());

        return messageBuilder.toString();
    }

    private String buildErrorMessage(Element element) {
        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder.append("Unsupported modifier for element of type: ");
        messageBuilder.append(element.getKind().name());
        messageBuilder.append(" in ");
        messageBuilder.append(element.getSimpleName().toString());

        return messageBuilder.toString();
    }
}
