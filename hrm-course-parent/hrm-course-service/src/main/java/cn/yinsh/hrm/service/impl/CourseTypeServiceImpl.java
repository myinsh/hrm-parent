package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.domain.CourseType;
import cn.yinsh.hrm.mapper.CourseTypeMapper;
import cn.yinsh.hrm.service.ICourseTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-18
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Override
    public List<CourseType> loaTypeTree() {
        return typeTreeByLoopAndMap();
    }

    private List<CourseType> typeTreeByLoopAndMap(){
        //查询所有课程类型
        List<CourseType> courseTypeList = baseMapper.selectList(null);
        //创建集合存放所有的以及类型
        List<CourseType> firstCourseType = new ArrayList<>();
        //创建map集合存放所有类型
        Map<Long,CourseType> courseTypeMap = new HashMap<>();
        //把所有的课程类型放入map集合中
        for (CourseType courseType : courseTypeList) {
            courseTypeMap.put(courseType.getId(),courseType);
        }
        //遍历所有的课程类型，判断pid是否为0，如果为0就是一级类型
        for (CourseType courseType : courseTypeList) {
            if (courseType.getPid()==0){
                //将一级类型存入list集合中
                firstCourseType.add(courseType);
            }else {
                //如果pid不为0，则表明不是一级类型.将其存放到children中
                CourseType parent = courseTypeMap.get(courseType.getPid());
                if (parent!=null){
                    parent.getChildren().add(courseType);
                }
            }
        }
        return firstCourseType;
    }
}
