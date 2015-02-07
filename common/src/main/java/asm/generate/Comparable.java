package asm.generate;

/**
 * @author youmoo
 * @since 2014-11-07 2:13 PM
 */
public interface Comparable extends Mesurable {
    int LESS = -1;
    int EQUAL = 0;
    int GREATER = 1;

    int compareTo(Object o);
}
