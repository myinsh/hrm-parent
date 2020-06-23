package cn.yinsh.hrm.service;

import cn.yinsh.hrm.controller.vo.CourseAddVo;
import cn.yinsh.hrm.domain.Course;
import cn.yinsh.hrm.query.CourseQuery;
import cn.yinsh.hrm.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-18
 */
public interface ICourseService extends IService<Course> {

    PageList<Course> pageAndAdvanced(CourseQuery query);

    void add(CourseAddVo courseAddVo);

    void onLine(List<Long> ids);

    void offLine(List<Long> ids);
}
