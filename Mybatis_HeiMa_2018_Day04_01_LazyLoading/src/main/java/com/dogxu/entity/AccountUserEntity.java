package com.dogxu.entity;

import java.io.Serializable;

/**
 * @author DogXu
 * @date 2020/8/5 13:48
 * @description:账户用户类，是账户类的子类
 */
public class AccountUserEntity extends AccountEntity implements Serializable {

    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() +" --- AccountUserEntity{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
