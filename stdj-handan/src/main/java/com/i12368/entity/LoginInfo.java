package com.i12368.entity;

import lombok.Data;

@Data
public class LoginInfo {
    private String username;
    private String password;
    private String fy;
    private String fydm;
    private String cbfyid;

    public LoginInfo() {
    }

    public LoginInfo(String username, String password, String fy, String fydm, String cbfyid) {
        this.username = username;
        this.password = password;
        this.fy = fy;
        this.fydm = fydm;
        this.cbfyid = cbfyid;
    }
}
