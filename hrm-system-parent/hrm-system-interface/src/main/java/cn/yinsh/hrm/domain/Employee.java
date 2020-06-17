package cn.yinsh.hrm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_employee")
public class Employee {
    @TableId(type = IdType.AUTO)
    private String id;
    private String username;
    private String password;
    @TableField("realName")
    private String realName;
    private String tel;
    private String email;
    @TableField("inputTime")
    private String inputTime;
    private String state;
    private String deptId;
    private String tenantId;
    private String type;

}
