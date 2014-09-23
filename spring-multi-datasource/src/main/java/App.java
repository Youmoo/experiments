import dao.UserDao;
import domain.User;
import support.SpringUtil;

import java.util.Date;


public class App {

    static UserDao userDao = SpringUtil.get(UserDao.class);

    public static void main(String[] args) {
        User user = new User();
        user.setCreated(new Date());
        user.setUsername("edward");
        userDao.insert(user);
        user.setUsername("jack");
        userDao.insert(user);
    }
}
