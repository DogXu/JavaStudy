package com.dogxu.dao;

import com.dogxu.entity.RoleEntity;

import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/5 15:14
 * @description:角色表对应的持久层接口
 */
public interface RoleDao {

    /***
     * 查询所有角色，并获取每个角色下的所有用户
     * @return
     */
    List<RoleEntity> selectAllRoleWithUsers();
}
