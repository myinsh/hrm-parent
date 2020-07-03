package cn.yinsh.hrm.controller.vo;

import lombok.Data;

@Data
public class RegisterVo {

    private String telephone;
    private String uuid;
    private String imageCode;
    private String password;

    private String smsCode;

}
