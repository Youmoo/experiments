package dao;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public abstract class BaseDao extends SqlMapClientDaoSupport {

    private static final Logger logger = Logger.getLogger(BaseDao.class);
    public static final int BATCH_SIZE = 200;

    /**
     * sql 命名空间
     */
    protected abstract String getNamespace();

    public void insert(String sql, Object param) {
        getSqlMapClientTemplate().insert(getNamespace() + "." + sql, param);
    }

}
