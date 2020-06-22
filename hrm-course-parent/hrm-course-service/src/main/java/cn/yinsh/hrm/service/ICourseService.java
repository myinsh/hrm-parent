package cn.yinsh.hrm.service;

import cn.yinsh.hrm.domain.Course;
import cn.yinsh.hrm.query.CourseQuery;
import cn.yinsh.hrm.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
