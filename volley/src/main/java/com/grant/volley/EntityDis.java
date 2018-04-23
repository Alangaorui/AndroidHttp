package com.grant.volley;

/**
 * Created by grant on 2018/4/23 0023.
 */

public class EntityDis {

    private String uId;
    private String loginPasswordisRight;
    private String userType;
    private String loginErrMsg;

    public EntityDis() {

    }

    public EntityDis(String uId, String loginPasswordisRight, String userType, String loginErrMsg) {
        this.uId = uId;
        this.loginPasswordisRight = loginPasswordisRight;
        this.userType = userType;
        this.loginErrMsg = loginErrMsg;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getLoginPasswordisRight() {
        return loginPasswordisRight;
    }

    public void setLoginPasswordisRight(String loginPasswordisRight) {
        this.loginPasswordisRight = loginPasswordisRight;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLoginErrMsg() {
        return loginErrMsg;
    }

    public void setLoginErrMsg(String loginErrMsg) {
        this.loginErrMsg = loginErrMsg;
    }
}
