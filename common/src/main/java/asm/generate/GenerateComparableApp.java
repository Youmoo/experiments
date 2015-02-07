package asm.generate;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

/**
 * 手动构造字节码文件
 *
 * @author youmoo
 * @since 2014-11-07 2:15 PM
 */
public class GenerateComparableApp {
    public static void main(String[] args) {
        ClassWriter writer = new ClassWriter(0);

        // define class header
        writer.visit(Opcodes.V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", new String[]{"asm/generate/Mesurable"});
        writer.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "LESS", "I", null, new Integer(-1)).visitEnd();
        writer.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "EQUAL", "I", null, new Integer(0)).visitEnd();
        writer.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "GREATER", "I", null, new Integer(1)).visitEnd();

        writer.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        writer.visitEnd();

        byte[] b = writer.toByteArray();//字节码文件

        Class clz = new ClassLoader() {//自定义一个ClassLoader并加载上面的字节码
            public Class defineClass(String name, byte[] b) {
                return defineClass(name, b, 0, b.length);
            }
        }.defineClass("pkg.Comparable", b);
    }
}
