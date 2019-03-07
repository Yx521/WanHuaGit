package com.example.lenovo.playandroid.beans.zl;

import java.util.List;

/**
 * @author quchao
 * @date 2018/2/26
 */

public class LoginData {
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LoginDatas getLoginData() {
        return mLoginData;
    }

    public void setLoginData(LoginDatas loginData) {
        mLoginData = loginData;
    }

    public LoginData(int errorCode, String errorMsg, LoginDatas loginData) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        mLoginData = loginData;

    }

    private String errorMsg;

    private LoginDatas mLoginData;

    public class LoginDatas {
        private String username;
        private String password;
        private String icon;
        private int type;
        private List<Integer> collectIds;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", mLoginData=" + mLoginData +
                '}';
    }
}
