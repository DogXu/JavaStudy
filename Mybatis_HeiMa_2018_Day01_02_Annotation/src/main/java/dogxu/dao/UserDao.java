package dogxu.dao;


import dogxu.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/3 16:15
 * @description:用户的持久层接口
 */
public interface UserDao {

    /***
     * 查询所有
     * @return
     */
    @Select("SELECT * FROM user;")
    List<UserEntity> selectAll();
}
