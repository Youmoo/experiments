package exception;

/**
 * dao层异常封装
 *
 * @author youmoo
 * @since 2014-09-17 11:10 AM
 */
public class DaoException extends RuntimeException {
    public DaoException(Throwable t) {
        super(t);
    }

    public DaoException(String msg, Throwable t) {
        super(msg, t);
    }
}
