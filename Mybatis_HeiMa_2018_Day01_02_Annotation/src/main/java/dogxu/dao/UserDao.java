package dogxu.dao;


import dogxu.entity.UserEntity;
import dogxu.entity.UserEntity2;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/3 16:15
 * @description:用户的持久层接口
 */
@CacheNamespace(blocking = true)//开启二级缓存支持
public interface UserDao {

    //TODO Mybatis注解，CRUD共四个注解分别如下：
    //@Select(),@Insert(),@Update(),@Delete()

    //注意事项：
    //1.若使用注解开发则resources下的相同路径下不能有改Dao的Xml文件存在，否则会报错。

    //TODO Mybatis注解，复杂关系映射的四个注解分别如下：
    //@Result,@Results,@One,@Many

    /***
     * 查询所有
     * @return
     */
    @Select("SELECT * FROM user")
    List<UserEntity> selectAll();

    /***
     * 查询所有
     * @return
     */
    @Select("SELECT * FROM user")
    @Results(id = "userWithAccountsMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "address",property = "address"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "id",
                    property = "accounts",
                    many = @Many(select = "dogxu.dao.AccountDao.selectByUid",
                            fetchType = FetchType.LAZY))
    })
    List<UserEntity> selectAllUserWithAccounts();

    /***
     * 通过id查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    UserEntity selectById(Integer id);

    /***
     * 查询所有
     * @return
     */
    @Select("SELECT * FROM user")
    @Results(id = "user2Map",value = {
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "birthday",property = "userBirthday")
    })//类似于xml中的ResultMap，有id值时可供其他方法重复使用
    List<UserEntity2> selectAllToUser2();

    /***
     * 通过id查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    @ResultMap(value = {"user2Map"})//resultMap中可以写多个ResultsId
    UserEntity2 selectByIdToUser2(Integer id);

    /***
     * 插入用户
     * @param user
     */
    @Insert("INSERT INTO user (username,address,sex,birthday) VALUES (#{username},#{address},#{sex},#{birthday})")
    void saveUser(UserEntity user);

    /***
     * 更新用户
     * @param user
     */
    @Update("UPDATE user SET username = #{username},address = #{address},sex = #{sex},birthday = #{birthday} WHERE id = #{id}")
    void updateUser(UserEntity user);


    /***
     * 通过用户id删除用户
     * @param id
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUserById(Integer id);
}
