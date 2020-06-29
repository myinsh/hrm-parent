package cn.yinsh.hrm.controller;

import cn.yinsh.hrm.controller.vo.CourseAddVo;
import cn.yinsh.hrm.domain.ESCourse;
import cn.yinsh.hrm.service.ICourseService;
import cn.yinsh.hrm.domain.Course;
import cn.yinsh.hrm.query.CourseQuery;
import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    public ICourseService courseService;

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public AjaxResult save(@RequestBody CourseAddVo courseAddVo){
        try {
            courseService.add(courseAddVo);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }


    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            courseService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Course get(@PathVariable("id")Long id)
    {
        return courseService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Course> list(){

        return courseService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public PageList<Course> page(@RequestBody CourseQuery query)
    {
        return courseService.pageAndAdvanced(query);
    }


    //课程上线
    @PostMapping("/onLine")
    public AjaxResult onLine(@RequestBody List<Long> ids){
        try {
            courseService.onLine(ids);
            return AjaxResult.me().setMessage("上线成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上线失败"+e.getMessage());
        }
    }
    //课程下线
    @PostMapping("/offLine")
    public AjaxResult offLine(@RequestBody List<Long> ids){

        try {
            courseService.offLine(ids);
            return AjaxResult.me().setMessage("下线成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("下线失败"+e.getMessage());
        }
    }
    @PostMapping("/pageOnline")
    public PageList<ESCourse> pageOnline(@RequestBody CourseQuery query){
        return courseService.pageOnline(query);
    }


}