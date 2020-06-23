package cn.yinsh.hrm.controller;

import cn.yinsh.hrm.domain.ESCourse;
import cn.yinsh.hrm.query.ESCourseQuery;
import cn.yinsh.hrm.repository.ESCourseRepository;
import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.PageList;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elasticsearch")
public class ESCourseController {
    @Autowired
    private ESCourseRepository esCourseRepository;

    @PostMapping("/save")
    public AjaxResult save(@RequestBody ESCourse courseDocument){
        try {
            esCourseRepository.save(courseDocument);
            return AjaxResult.me().setMessage("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("添加失败"+e.getMessage());
        }
    }

    @PostMapping("/saveAll")
    public AjaxResult saveAll(@RequestBody List<ESCourse> courseDocuments){
        try {
            esCourseRepository.saveAll(courseDocuments);
            return AjaxResult.me().setMessage("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("添加失败"+e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public AjaxResult delete(@RequestParam("id") Long id){
        try {
            esCourseRepository.deleteById(id);
            return AjaxResult.me().setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败"+e.getMessage());
        }
    }
    //批量删除
    @DeleteMapping("/deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        try {
            for (Long id : ids) {
                esCourseRepository.deleteById(id);
            }
            return AjaxResult.me().setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败");
        }
    }
    @PostMapping("/search")
    public PageList<ESCourse> search(@RequestBody ESCourseQuery documentQuery){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //创建查询条件
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //关键字查询
        if(!StringUtils.isEmpty(documentQuery.getKeyword())){
            boolQueryBuilder.must(new MatchQueryBuilder("all",documentQuery.getKeyword()));
        }
        //过滤
        List<QueryBuilder> filter = boolQueryBuilder.filter();
        //过滤课程类型
        if (documentQuery.getCourseTypeId()!=null){
            filter.add(new TermQueryBuilder("courseTypeId",documentQuery.getCourseTypeId()));
        }
        //等级
        if (documentQuery.getGrade()!=null){
            filter.add(new TermQueryBuilder("Grade",documentQuery.getGrade()));
        }
        builder.withQuery(boolQueryBuilder);
        //分页
        builder.withPageable(PageRequest.of(documentQuery.getPageNum()-1,documentQuery.getPageSize()));
        //查询后将查询结果封装到list集合中
        Page<ESCourse> search = esCourseRepository.search(builder.build());
        PageList<ESCourse> pageList = new PageList<>();
        pageList.setRows(search.getContent());
        pageList.setTotal(pageList.getTotal());
        return pageList;
    }


}
