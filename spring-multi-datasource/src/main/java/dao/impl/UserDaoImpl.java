package dao.impl;


import dao.BaseDao;
import dao.UserDao;
import domain.User;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public void insert(User user) {
        super.insert("insert", user);
    }

    @Override
    protected String getNamespace() {
        return "User";
    }
}
