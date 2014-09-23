package dao;


import domain.User;

/**
 * 用来从数据库获取user对象
 */
public interface UserDao {

    /**
     * 插入一个新用户
     */
    public void insert(User user);
}
