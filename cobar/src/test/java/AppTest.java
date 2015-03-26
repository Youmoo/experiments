import org.aaron.cobar.bean.TbUser;
import org.aaron.cobar.dao.TbUserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author youmoo
 * @since 2014-05-10 上午11:50
 */
public class AppTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-common.xml");
    TbUserDao tbUserDao = (TbUserDao) context.getBean("tbUserDao");

    @Test
    public void testGetTbUser() {
        TbUser tbUser = tbUserDao.getByUserId(123L);
    }

    @Test
    public void testInsertTbUser() {
        TbUser tbUser = new TbUser();
        tbUser.setUserId(123L);
        tbUser.setNickName("且教芯芒");
        tbUser.setCreated(new Date());
        tbUser.setModified(tbUser.getCreated());
        tbUserDao.insert(tbUser);
    }

}
