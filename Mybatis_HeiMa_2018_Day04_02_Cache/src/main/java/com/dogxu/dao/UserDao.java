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
     * 查询所有(default select)
     * @return
     */
    List<UserEntity> selectAll();

    /***
     * 通过id查询用户(一级缓存)
     * @param id
     * @return
     */
    UserEntity selectByIdWithFirstLevelCache(Integer id);

    /***
     * 通过id查询用户(二级缓存)
     * @param id
     * @return
     */
    UserEntity selectByIdWithSecondLevelCache(Integer id);
}
