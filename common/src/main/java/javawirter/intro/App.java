package javawirter.intro;

import com.squareup.javawriter.JavaWriter;

import java.io.FileWriter;
import java.util.EnumSet;

import static javax.lang.model.element.Modifier.*;

/**
 * 代码自动生成
 *
 * @author youmoo
 * @since 2014-09-04 2:31 PM
 */
public class App {

    public static void main(String[] args) throws Exception {
        FileWriter out = new FileWriter("A.java");
        JavaWriter writer = new JavaWriter(out);
        writer.emitPackage("com.example")
                .beginType("com.example.Person", "class", EnumSet.of(PUBLIC, FINAL))
                .emitField("Date", "firstName", EnumSet.of(PRIVATE))
                .emitField("String", "lastName", EnumSet.of(PRIVATE))
                .emitJavadoc("Returns the person's full name.")
                .beginMethod("String", "getName", EnumSet.of(PUBLIC))
                .emitStatement("return firstName + \" \" + lastName")
                .endMethod()
                .endType();
        out.close();

    }
}
