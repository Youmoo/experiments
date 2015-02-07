package asm.parse;

import org.objectweb.asm.ClassReader;

/**
 * @author youmoo
 * @since 2014-11-07 2:05 PM
 */
public class ClassPrinterApp {
    public static void main(String[] args) throws Exception {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cp, 0);
    }
}
