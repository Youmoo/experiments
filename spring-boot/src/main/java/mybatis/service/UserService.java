package mybatis.service;

import mybatis.domain.User;
import mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author youmoo
 * @since 2015-03-26 1:40 PM
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    SqlSession sqlSession;

    public User getUser(Long userId) {
        return userMapper.getUser(userId);
    }

    public User anotherGetUser(Long userId) {
        return sqlSession.selectOne("mybatis.mapper.UserMapper.getUser", userId);
    }

    public int insert(Long userId, String nickName) {
        return userMapper.insert(userId, nickName);
    }

    @Transactional/*不配置这一项，会有一个插入成功，一个插入失败*/
    public void insertMany() {
        userMapper.insert(4L, "one");
        userMapper.insert(4L, "two");
    }
}
