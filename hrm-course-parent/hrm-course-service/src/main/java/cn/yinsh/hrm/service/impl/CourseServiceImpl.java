package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.domain.Course;
import cn.yinsh.hrm.mapper.CourseMapper;
import cn.yinsh.hrm.query.CourseQuery;
import cn.yinsh.hrm.service.ICourseService;
import cn.yinsh.hrm.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public PageList<Course> pageAndAdvanced(CourseQuery query) {
        IPage<Course> iPage = baseMapper.selectByQuery(new Page<Course>(query.getPageNum(), query.getPageSize()), query);
        PageList<Course> pageList = new PageList<>();
        pageList.setTotal(iPage.getTotal());
        pageList.setRows(iPage.getRecords());
        return pageList;
    }
}
