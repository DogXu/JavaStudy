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
     * 通过Id获取实体类
     * @param id
     * @return
     */
    UserEntity selectById(Integer id);

    /***
     * 查询所有用户并获取每个用户下所有的账户信息
     * @return
     */
    List<UserEntity> selectAllUserWithAccounts();
}
