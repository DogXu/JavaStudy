package com.dogxu.dao;

import com.dogxu.entity.UserEntity;

import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/3 16:15
 * @description:用户的持久层接口
 */
public interface UserDao {

    /***
     * 查询所有用户并获取每个用户下所有的账户信息
     * @return
     */
    List<UserEntity> selectAllUserWithAccounts();

    /***
     * 查询所有用户并获取每个用户下所有的角色信息
     * @return
     */
    List<UserEntity> selectAllUserWithRoles();
}
