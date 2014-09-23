package functional;

/**
 * @autor youmoo
 * @since 2014-04-16 下午5:23
 */
public interface Predictor<E> {
    /**
     * 测试元素e是否符合某个条件
     *
     * @param e
     */
    public boolean test(E e);
}
