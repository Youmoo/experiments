package mybatis.mapper;

import mybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author youmoo
 * @since 2015-03-26 1:31 PM
 */
public interface UserMapper {
    /**
     * 注意查询的列名要于业务类一致
     */
    @Select("SELECT user_id as userId,nick_name as nickName FROM tb_user WHERE user_id = #{userId}")
    User getUser(@Param("userId") Long userId);

    @Insert("insert into tb_user (user_id,nick_name) values (#{userId},#{nickName})")
    int insert(@Param("userId") Long userId, @Param("nickName") String nickName);

}
