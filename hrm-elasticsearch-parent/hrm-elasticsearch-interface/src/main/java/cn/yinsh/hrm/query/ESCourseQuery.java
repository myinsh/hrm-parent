package cn.yinsh.hrm.query;

import lombok.Data;

@Data
public class ESCourseQuery extends BaseQuery {
    private Long courseType;
    private Float maxPrice;
    private Float minPrice;
    private Long tenantId;
    private String columnName;
    private String orderType;

}
