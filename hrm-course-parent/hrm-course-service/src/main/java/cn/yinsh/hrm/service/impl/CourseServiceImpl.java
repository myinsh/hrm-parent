package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.client.ESCourseClient;
import cn.yinsh.hrm.client.SystemClient;
import cn.yinsh.hrm.controller.vo.CourseAddVo;
import cn.yinsh.hrm.domain.Course;
import cn.yinsh.hrm.domain.CourseDetail;
import cn.yinsh.hrm.domain.ESCourse;
import cn.yinsh.hrm.domain.SystemdictionaryItem;
import cn.yinsh.hrm.mapper.CourseDetailMapper;
import cn.yinsh.hrm.mapper.CourseMapper;
import cn.yinsh.hrm.mapper.CourseTypeMapper;
import cn.yinsh.hrm.query.CourseQuery;
import cn.yinsh.hrm.service.ICourseService;
import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private ESCourseClient esCourseClient;
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


    @Override
    @Transactional
    public void onLine(List<Long> ids) {
        System.out.println("当前时间："+System.currentTimeMillis());
        //批量修改课程上线时间
        baseMapper.onLine(System.currentTimeMillis(),ids);
        //查询所有课程信息
        List<Course> courses = baseMapper.selectBatchIds(ids);
        //调用批量保存
        AjaxResult ajaxResult = esCourseClient.saveAll(courses2Docs(courses));
        if (!ajaxResult.isSuccess()){
            throw new RuntimeException(ajaxResult.getMessage());
        }
    }

    private List<ESCourse> courses2Docs(List<Course> courses) {
        List<ESCourse> docs = new ArrayList<>();
        ESCourse doc = null;
        for (Course cours : courses) {
            doc = course2Docs(cours);
            docs.add(doc);
        }
        return docs;

    }

    //课程对象的转换
    private ESCourse course2Docs(Course courses) {
        ESCourse doc = new ESCourse();
        doc.setId(courses.getId());
        doc.setName(courses.getName());
        doc.setUsers(courses.getUsers());
        doc.setCourseTypeId(courses.getCourseTypeId());
        CourseDetail courseDetail = courseDetailMapper.selectOne(new QueryWrapper<CourseDetail>().eq("course_id", courses.getId()));
        doc.setDescription(courseDetail!=null?courseDetail.getDescription():"");

        doc.setIntro(courseDetail!=null?courseDetail.getIntro():"");
        //课程名称
        String courseName = courses.getName();
        //课程类型名称
        String typeName = courseTypeMapper.selectById(courses.getCourseTypeId()).getName();
        //机构名称
        String tenantName = courses.getTenantName();
        //加上空格后，空格前后会自动分词
        String all = courseName+" "+typeName  +" "+tenantName;
        doc.setAll(all);
        doc.setStartTime(courses.getStartTime());
        doc.setGrade(courses.getGrade());

        return doc;

    }
    //课程下线
    @Transactional
    @Override
    public void offLine(List<Long> ids) {
        //批量修改课程下线时间
        baseMapper.offLine(System.currentTimeMillis(), ids);
        //删除es
        AjaxResult ajaxResult = esCourseClient.deleteAll(ids);
        if (!ajaxResult.isSuccess()){
            throw new RuntimeException(ajaxResult.getMessage());
        }
    }
}
