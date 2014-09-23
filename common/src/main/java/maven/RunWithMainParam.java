package maven;

/**
 * mvn clean compile exec:java -Dexec.mainClass=maven.RunWithMainParam -s ../settings.xml
 *
 * @author youmoo
 * @since 2014-09-07 1:08 PM
 */
public class RunWithMainParam {
    public static void main(String[] args) {
        System.out.println("--------Hello World!\n");
    }
}
