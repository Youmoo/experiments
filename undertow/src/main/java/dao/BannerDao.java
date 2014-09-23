package dao;

import domain.Banner;

import java.util.Date;

/**
 * @author youmoo
 * @since 2014-09-17 10:57 AM
 */
public interface BannerDao {

    /**
     * 插入一条新banner
     *
     * @param banner
     */
    public void insert(Banner banner);

    /**
     * 获取一个banner
     *
     * @return
     */
    public Banner get(Date date);
}
