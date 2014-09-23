package support;

/**
 * @autor youmoo
 * @since 2014-03-30 2:27 PM
 */
public class CustomerContextHolder {
    public static final String DATA_SOURCE_A = "dataSourceA";

    public static final String DATA_SOURCE_B = "dataSourceB";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
