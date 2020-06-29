package cn.yinsh.hrm.controller;

import cn.yinsh.hrm.domain.ESCourse;
import cn.yinsh.hrm.query.ESCourseQuery;
import cn.yinsh.hrm.repository.ESCourseRepository;
import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.PageList;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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

    @PostMapping("/page")
    public PageList<ESCourse> page(@RequestBody ESCourseQuery esCourseQuery){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //查询
        if(StringUtils.isNotEmpty(esCourseQuery.getKeyword())){
            boolQueryBuilder.must(new MatchQueryBuilder("all",esCourseQuery.getKeyword()));
        }
        if(esCourseQuery.getCourseType()!=null){
            boolQueryBuilder.must(new TermQueryBuilder("courseTypeId",esCourseQuery.getCourseType()));
        }
        if(esCourseQuery.getTenantId()!=null){
            boolQueryBuilder.must(new TermQueryBuilder("tenantId",esCourseQuery.getTenantId()));
        }
        //过滤
        List<QueryBuilder> filter = boolQueryBuilder.filter();
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("price");
        if(esCourseQuery.getMaxPrice()!=null){
            rangeQueryBuilder.lte(esCourseQuery.getMaxPrice());
        }
        if(esCourseQuery.getMinPrice()!=null){
            rangeQueryBuilder.gte(esCourseQuery.getMinPrice());
        }
        filter.add(rangeQueryBuilder);

        builder.withQuery(boolQueryBuilder);
        //排序
        String fieldName = "price";
        SortOrder order = SortOrder.ASC;

        if(StringUtils.isNotEmpty(esCourseQuery.getColumnName())){
            switch (esCourseQuery.getColumnName()){
                case "xp":
                    fieldName = "startTime";
                    break;
                case "jg":
                    fieldName = "price";
                    break;
            }
        }
        if(StringUtils.isNotEmpty(esCourseQuery.getOrderType())){
            switch (esCourseQuery.getOrderType()){
                case "asc":
                    order = SortOrder.ASC;
                    break;
                case "desc":
                    order = SortOrder.DESC;
                    break;
            }
        }
        builder.withSort(new FieldSortBuilder(fieldName).order(order));
        //分页
        builder.withPageable(PageRequest.of(esCourseQuery.getPageNum()-1,esCourseQuery.getPageSize()));
        Page<ESCourse> docPage = esCourseRepository.search(builder.build());
        PageList<ESCourse> pageList = new PageList<>();
        pageList.setTotal(docPage.getTotalElements());
        pageList.setRows(docPage.getContent());

        return pageList;
    }


}
