package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.client.SystemClient;
import cn.yinsh.hrm.controller.vo.CourseAddVo;
import cn.yinsh.hrm.domain.Course;
import cn.yinsh.hrm.domain.CourseDetail;
import cn.yinsh.hrm.domain.SystemdictionaryItem;
import cn.yinsh.hrm.mapper.CourseDetailMapper;
import cn.yinsh.hrm.mapper.CourseMapper;
import cn.yinsh.hrm.query.CourseQuery;
import cn.yinsh.hrm.service.ICourseService;
import cn.yinsh.hrm.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-18
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private CourseDetailMapper courseDetailMapper;

    @Override
    public void add(CourseAddVo courseAddVo) {
        //往课程表中添加
        Course course = new Course();
        BeanUtils.copyProperties(courseAddVo,course);
        SystemdictionaryItem systemdictionaryItem = systemClient.get(course.getGrade());
        if (systemdictionaryItem!=null){
            course.setGradeName(systemdictionaryItem.getName());
        }
        course.setTenantId(26L);
        course.setTenantName("源码时代");
        course.setUserId(42L);
        course.setStatus(0);
        course.setUserName("admin");
        baseMapper.insert(course);
        //往课程详情表中添加数据
        CourseDetail courseDetail = new CourseDetail();
        BeanUtils.copyProperties(courseAddVo,courseDetail);
        courseDetail.setCourseId(course.getId());
        courseDetailMapper.insert(courseDetail);

    }

    @Override
    public PageList<Course> pageAndAdvanced(CourseQuery query) {
        IPage<Course> iPage = baseMapper.selectByQuery(new Page<Course>(query.getPageNum(), query.getPageSize()), query);
        PageList<Course> pageList = new PageList<>();
        pageList.setTotal(iPage.getTotal());
        pageList.setRows(iPage.getRecords());
        return pageList;
    }
}
