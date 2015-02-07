package ast;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * @author youmoo
 * @since 2015-01-23 1:00 PM
 */
@SupportedAnnotationTypes("ast.NoArgsConstructor")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NoArgsConstructorProcessor extends AbstractProcessor {
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        for (TypeElement type : annotations) {
            processNoArgsConstructorClasses(env, type);
        }
        return true;
    }

    private void processNoArgsConstructorClasses(RoundEnvironment env, TypeElement type) {
        for (Element element : env.getElementsAnnotatedWith(type)) {
            processClass(element);
        }
    }

    private void processClass(Element element) {
        if (!doesClassContainNoArgsConstructor(element)) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Class " + element + " needs a No-Args Constructor");
        }
    }

    private boolean doesClassContainNoArgsConstructor(Element el) {
        for (Element subelement : el.getEnclosedElements()) {
            if (subelement.getKind() == ElementKind.CONSTRUCTOR &&
                    subelement.getModifiers().contains(Modifier.PUBLIC)) {
                TypeMirror mirror = subelement.asType();
                if (mirror.accept(noArgsVisitor, null)) return true;
            }
        }
        return false;
    }

    private static final TypeVisitor<Boolean, Void> noArgsVisitor =
            new SimpleTypeVisitor6<Boolean, Void>() {
                public Boolean visitExecutable(ExecutableType t, Void v) {
                    return t.getParameterTypes().isEmpty();
                }
            };
}
