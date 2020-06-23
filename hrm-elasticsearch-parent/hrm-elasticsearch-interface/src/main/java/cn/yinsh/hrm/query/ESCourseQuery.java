package cn.yinsh.hrm.query;

import lombok.Data;

@Data
public class ESCourseQuery extends BaseQuery {
    private String description;
    private String name;
    private String intro;
    private Long courseTypeId;
    private Long grade;

}
