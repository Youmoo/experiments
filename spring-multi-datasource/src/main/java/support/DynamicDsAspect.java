package support;

import domain.User;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * aop用来动态设置数据源所在key
 *
 * @autor youmoo
 * @since 2014-03-30 2:36 PM
 */
public class DynamicDsAspect implements MethodBeforeAdvice {
/*    @Pointcut("execution (* *..*DaoImpl.insert*(..)))")
    public void serviceExecution() {
    }

    @Before("serviceExecution ()")
    public void setDynamicDataSource(JoinPoint jp) {
        for (Object o : jp.getArgs()) {
            CustomerContextHolder.setCustomerType("");
        }
    }*/

    /**
     * 根据用户名动态分配数据源
     */
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        User user = (User) objects[0];
        if (user.getUsername().charAt(0) < 'h') {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
        } else {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_B);
        }
    }
}
