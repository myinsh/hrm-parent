package cn.yinsh.hrm.controller.vo;

import cn.yinsh.hrm.domain.CourseType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CrumbVo {

    private CourseType currentType;

    private List<CourseType> otherTypes = new ArrayList<>();

}
