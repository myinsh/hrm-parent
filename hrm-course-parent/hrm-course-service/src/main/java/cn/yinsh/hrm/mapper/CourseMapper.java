package cn.yinsh.hrm.mapper;

import cn.yinsh.hrm.domain.Course;
import cn.yinsh.hrm.query.CourseQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yinsh
 * @since 2020-06-18
 */
public interface CourseMapper extends BaseMapper<Course> {

    IPage<Course> selectByQuery(Page<Course> coursePage, @Param("query") CourseQuery query);

    void onLine(@Param("time") long l,@Param("ids") List<Long> ids);

    void offLine(@Param("time") long l,@Param("ids") List<Long> ids);
}
