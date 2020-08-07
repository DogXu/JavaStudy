package dogxu.dao;


import dogxu.entity.AccountEntity;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/5 13:47
 * @description:账户的持久层接口
 */
public interface AccountDao {

    /***
     * 查询所有账户，账户包含所属用户的用户实体类(延迟加载)
     * @return
     */
    @Select("SELECT * from account")
    @Results(id = "accountWithUserMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(column = "uid",
                    property = "user",
                    one = @One(select = "dogxu.dao.UserDao.selectById",fetchType = FetchType.LAZY))
    })
    List<AccountEntity> selectAll();
    //FetchType.LAZY：延迟加载，通常在一对一的时候选用；
    //FetchType.EAGER：立即加载，通常在一对多的时候选用；

    /***
     * 通过id查询账户
     * @param id
     * @return
     */
    @Select("SELECT * FROM account WHERE uid = #{uid}")
    List<AccountEntity> selectByUid(Integer uid);
}
