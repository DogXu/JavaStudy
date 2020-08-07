package com.dogxu.dao;

import com.dogxu.entity.AccountEntity;
import com.dogxu.entity.UserEntity;

import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/5 13:47
 * @description:账户的持久层接口
 */
public interface AccountDao {

    /***
     * 查询所有账户，账户包含所属用户的用户实体类
     * @return
     */
    List<AccountEntity> selectAllAccountWithUser();

    /***
     * 通过uId获取实体类
     * @param uId
     * @return
     */
    List<AccountEntity> selectByUid(Integer uId);
}
