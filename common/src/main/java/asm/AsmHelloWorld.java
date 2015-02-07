package asm;

import org.objectweb.asm.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author youmoo
 * @since 2014-10-22 10:35 AM
 */
public class AsmHelloWorld {


    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ClassVisitor cl = new ClassVisitor(Opcodes.ASM4) {

            /**
             * Called when a class is visited. This is the method called first
             */
            @Override
            public void visit(int version, int access, String name,
                              String signature, String superName, String[] interfaces) {
                System.out.println("Visiting class: " + name);
                System.out.println("Class Major Version: " + version);
                System.out.println("Super class: " + superName);
                super.visit(version, access, name, signature, superName, interfaces);
            }

            /**
             * Invoked only when the class being visited is an inner class
             */
            @Override
            public void visitOuterClass(String owner, String name, String desc) {
                System.out.println("Outer class: " + owner);
                super.visitOuterClass(owner, name, desc);
            }

            /**
             *Invoked when a class level annotation is encountered
             */
            @Override
            public AnnotationVisitor visitAnnotation(String desc,
                                                     boolean visible) {
                System.out.println("Annotation: " + desc);
                return super.visitAnnotation(desc, visible);
            }

            /**
             * When a class attribute is encountered
             */
            @Override
            public void visitAttribute(Attribute attr) {
                System.out.println("Class Attribute: " + attr.type);
                super.visitAttribute(attr);
            }

            /**
             *When an inner class is encountered
             */
            @Override
            public void visitInnerClass(String name, String outerName,
                                        String innerName, int access) {
                System.out.println("Inner Class: " + innerName + " defined in " + outerName);
                super.visitInnerClass(name, outerName, innerName, access);
            }

            /**
             * When a field is encountered
             */
            @Override
            public FieldVisitor visitField(int access, String name,
                                           String desc, String signature, Object value) {
                System.out.println("Field: " + name + " " + desc + " value:" + value);
                return super.visitField(access, name, desc, signature, value);
            }


            @Override
            public void visitEnd() {
                System.out.println("Method ends here");
                super.visitEnd();
            }

            /**
             * When a method is encountered
             */
            @Override
            public MethodVisitor visitMethod(int access, String name,
                                             String desc, String signature, String[] exceptions) {
                System.out.println("Method: " + name + " " + desc);
                return super.visitMethod(access, name, desc, signature, exceptions);
            }

            /**
             * When the optional source is encountered
             */
            @Override
            public void visitSource(String source, String debug) {
                System.out.println("Source: " + source);
                super.visitSource(source, debug);
            }


        };
        InputStream in = AsmHelloWorld.class.getResourceAsStream("/java/lang/String.class");
        ClassReader classReader = new ClassReader(in);
        classReader.accept(cl, 0);

    }
}
