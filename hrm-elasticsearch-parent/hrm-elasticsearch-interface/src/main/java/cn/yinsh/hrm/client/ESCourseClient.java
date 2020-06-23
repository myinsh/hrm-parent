package cn.yinsh.hrm.client;

import cn.yinsh.hrm.client.impl.ESCourseClientImpl;
import cn.yinsh.hrm.domain.ESCourse;
import cn.yinsh.hrm.query.ESCourseQuery;
import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "elasticsearch-service",fallback = ESCourseClientImpl.class,path = "/elasticsearch")
public interface ESCourseClient {
    @PostMapping("/save")
    AjaxResult save(@RequestBody ESCourse courseDocument);
    @PostMapping("/saveAll")
    AjaxResult saveAll(@RequestBody List<ESCourse> courseDocuments);
    @DeleteMapping("/delete")
    AjaxResult delete(@RequestParam("id") Long id);
    @DeleteMapping("/deleteAll")
    AjaxResult deleteAll(@RequestBody List<Long> ids);
    @PostMapping("/search")
    PageList<ESCourse> search(@RequestBody ESCourseQuery documentQuery);
}
