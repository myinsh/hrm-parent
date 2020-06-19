package cn.yinsh.hrm.controller.vo;

import lombok.Data;

@Data
public class RegisterVo {

    private String companyName;
    private String companyNum;
    private Long registerTime;
    private Integer state = 1;
    private String address;
    private String logo;
    private Long tenantType;
    private Long mealId;
    private String username;
    private String password;


}
