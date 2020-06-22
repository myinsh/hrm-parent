package cn.yinsh.hrm.controller.vo;

import lombok.Data;

@Data
public class CourseAddVo {
    private String name;
    private String users;
    private Long courseTypeId;
    private Long grade;
    private String pic;
    private String intro;
    private String description;
}
