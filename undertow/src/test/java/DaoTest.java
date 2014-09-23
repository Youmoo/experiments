import dao.BannerDao;
import dao.impl.BannerDaoImpl;
import domain.Banner;
import org.junit.Test;

import java.util.Date;

/**
 * @author youmoo
 * @since 2014-09-17 11:52 AM
 */
public class DaoTest {
    @Test
    public void testBanner() {
        Banner banner = new Banner();
        banner.setDesc("测试banner");
        banner.setImg("http://nzg.superboss.cc/img/logo_performance.png");
        banner.setLink("http://www.baidu.com/");
        banner.setStart(new Date());
        banner.setEnd(new Date());
        banner.setPrioirty(1);
        banner.setIsValid("Y");
        BannerDao bannerDao = new BannerDaoImpl();
        bannerDao.insert(banner);
    }
}
