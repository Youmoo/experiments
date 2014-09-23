package org.aaron.cobar.dao;

import org.aaron.cobar.bean.TbUser;
import org.aaron.cobar.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youmoo
 * @since 2014-09-13 12:43 PM
 */
@Repository
public class TbUserDao {
    @Autowired
    SqlMapClientTemplate sqlMapClientTemplate;

    public TbUser getByUserId(Long userId) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put(Constants.Keys.ROUTER_KEY, userId);
        return (TbUser) sqlMapClientTemplate.queryForObject("TbUserDao.getByUserId", params);
    }

    public void insert(TbUser tbUser) {
        sqlMapClientTemplate.insert("TbUserDao.insert", tbUser);
    }
}
