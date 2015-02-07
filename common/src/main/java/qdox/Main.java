package qdox;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;

import java.io.File;

/**
 * parse doclet
 *
 * @author youmoo
 * @since 2015-01-19 9:50 AM
 */
public class Main {
    public static void main(String[] args) throws Exception {
        JavaDocBuilder docBuilder = new JavaDocBuilder();
        docBuilder.addSource(new File("/home/yoomoo/workspace/experiments/common/src/main/java/qdox/Vo.java"));

        JavaClass javaClass = docBuilder.getClassByName("qdox.Vo");
        JavaField[] javaFields = javaClass.getFields();
        for (JavaField field : javaFields) {
            System.out.println("field:" + field.getName());
            DocletTag tag = field.getTagByName("label");
            if (tag != null) {
                System.out.println("label:" + tag.getValue());
            }
            tag = field.getTagByName("tip");
            if (tag != null) {
                System.out.println("tip:" + tag.getValue());
            }
        }

    }
}
