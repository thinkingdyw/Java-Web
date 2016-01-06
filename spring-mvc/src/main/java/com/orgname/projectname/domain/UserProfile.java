package com.orgname.projectname.domain;

import javax.validation.constraints.NotNull;

public class UserProfile {

    private String nickName;
    @NotNull(message="用户名为空")
    private String userName;
    private byte[] pwd;
    private String email;
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public byte[] getPwd() {
        return pwd;
    }
    public void setPwd(byte[] pwd) {
        this.pwd = pwd;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
