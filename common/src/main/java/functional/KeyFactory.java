package functional;

/**s
 * @author youmoo
 * @since 2014-10-15 6:05 PM
 */
public interface KeyFactory<K, V> {
    public K makeKey(V v);
}
