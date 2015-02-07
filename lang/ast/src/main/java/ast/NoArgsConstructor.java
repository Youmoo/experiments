package ast;

import java.lang.annotation.*;

/**
 * @author youmoo
 * @since 2015-01-23 12:59 PM
 */
@Inherited
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface NoArgsConstructor {
}
