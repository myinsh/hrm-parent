package cn.yinsh.hrm.service;

import cn.yinsh.hrm.controller.vo.CrumbVo;
import cn.yinsh.hrm.domain.CourseType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-18
 */
public interface ICourseTypeService extends IService<CourseType> {

    List<CourseType> loaTypeTree();

    List<CrumbVo> loadCrumbs(Long courseTypeId);
}
