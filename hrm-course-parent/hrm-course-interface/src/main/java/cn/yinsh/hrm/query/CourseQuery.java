package cn.yinsh.hrm.query;

import lombok.Data;

/**
 * <p>
 *  查询参数对象
 * </p>
 *
 * @author yinsh
 * @since 2020-06-18
 */
@Data
public class CourseQuery extends BaseQuery {


    private Integer status;
    private Long courseType;
    private Float maxPrice;
    private Float minPrice;
    private Long tenantId;
    private String columnName;
    private String orderType;

}