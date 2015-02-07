package asm.types;


import org.objectweb.asm.Type;

/**
 * @author youmoo
 * @since 2014-11-07 3:36 PM
 */
public class TypeApp {
    public static void main(String[] args) {

        String objType=Type.getType("Ljava/lang/Object;").getInternalName();
    }
}
