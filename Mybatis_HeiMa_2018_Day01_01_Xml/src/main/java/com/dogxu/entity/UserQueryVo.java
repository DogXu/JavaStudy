package com.dogxu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/4 15:44
 * @description:将多个对象/参数封装成毅哥对象作为查询条件
 */
public class UserQueryVo implements Serializable {

    private UserEntity user;
    private Integer id;
    private List<Integer> ids;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
