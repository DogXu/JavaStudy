package com.dogxu.dao;

import com.dogxu.entity.UserEntity;
import com.dogxu.entity.UserEntity2;
import com.dogxu.entity.UserQueryVo;

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
     * 通过用户名模糊查询用户(LIKE & #{})
     * @return
     */
    List<UserEntity> selectByUsernameWithPercent(String username);

    /***
     * 通过用户名模糊查询用户(LIKE & ${value})
     * @return
     */
    List<UserEntity> selectByUsernameWithoutPercent(String username);

    /***
     * 通过组合参数对象查询用户(QueryVo)
     * @return
     */
    List<UserEntity> selectByVo(UserQueryVo vo);

    /***
     * 通过组合参数对象中的ID集合查询用户(QueryVo & WHERE & IF & FOREACH)
     * @return
     */
    List<UserEntity> selectByForeach(UserQueryVo vo);

    /***
     * 查询所有(resultMap)
     * @return
     */
    List<UserEntity2> selectAllToUserMap();

    /***
     * 通过条件参数查询用户(IF)
     * @return
     */
    List<UserEntity> selectByConditionWithIf(UserEntity userEntity);

    /***
     * 通过条件参数查询用户(IF & WHERE)
     * @return
     */
    List<UserEntity> selectByConditionWithWhere(UserEntity userEntity);

    /***
     * 保存用户(default insert)
     * @param userEntity
     */
    void saveUserWithoutReturnId(UserEntity userEntity);

    /***
     * 保存用户返回插入ID(selectKey & SELECT LAST_INSERT_ID())
     * @param userEntity
     */
    int saveUserWithReturnId(UserEntity userEntity);

    /***
     * 更新用户(default update)
     * @param userEntity
     */
    void updateUser(UserEntity userEntity);

    /***
     * 删除用户(default delete)
     * @param userEntity
     */
    void deleteUser(UserEntity userEntity);
}
