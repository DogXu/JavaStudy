package com.dogxu.dao;

import com.dogxu.entity.AccountEntity;
import com.dogxu.entity.AccountUserEntity;

import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/5 13:47
 * @description:账户的持久层接口
 */
public interface AccountDao {

    /***
     * 查询所有账户，同时获取账户所属用户的用户名及用户地址
     * @return
     */
    List<AccountUserEntity> selectAllAccountUser();

    /***
     * 查询所有账户，账户包含所属用户的用户实体类
     * @return
     */
    List<AccountEntity> selectAllAccountWithUser();
}
