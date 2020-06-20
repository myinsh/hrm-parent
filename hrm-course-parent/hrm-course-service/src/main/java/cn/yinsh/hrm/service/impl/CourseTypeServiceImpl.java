package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.client.CacheClient;
import cn.yinsh.hrm.domain.CourseType;
import cn.yinsh.hrm.mapper.CourseTypeMapper;
import cn.yinsh.hrm.service.ICourseTypeService;
import cn.yinsh.hrm.util.AjaxResult;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.internal.operators.SingleOperatorOnErrorResumeNext;
import sun.misc.Cache;

import java.security.Key;
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
    @Autowired
    private CacheClient cacheClient;

    private final String KEY = "coursetype:tree";
    @Override
    public List<CourseType> loaTypeTree() {
        List<CourseType> courseTypeList = new ArrayList<>();
        //自旋获取所资源
        while (true){
            //首先查询redis中是否有课程类型的数据
            AjaxResult ajaxResult = cacheClient.get(KEY);
            String courseTypeJSON = (String) ajaxResult.getResultObj();
            //如果redis中有课程类型数据
            if(StringUtils.isNotEmpty(courseTypeJSON)){
                //则使用FastJson将json数据转换为list后直接返回
                courseTypeList = JSONObject.parseArray(courseTypeJSON,CourseType.class);
                return courseTypeList;
            }
            try {
                //上锁
                ajaxResult = cacheClient.setnx("courseTypeKey","1");
                Integer result = (Integer) ajaxResult.getResultObj();
                //如果result的值是1，表示上锁成功
                if(result==1){
                    //如果redis中没有数据，则查询数据库
                    courseTypeList = typeTreeByLoopAndMap();
                    //然后将数据缓存到redis中
                    courseTypeJSON = JSONObject.toJSONString(courseTypeList);
                    //设置过期时间
                    cacheClient.setex(KEY,courseTypeJSON,10*60);
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                cacheClient.deleteKey("courseTypeKey");
            }
        }
        //返回数据
        return courseTypeList;
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
