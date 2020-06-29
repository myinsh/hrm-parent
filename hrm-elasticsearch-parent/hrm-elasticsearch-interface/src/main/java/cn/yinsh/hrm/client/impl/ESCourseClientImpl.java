package cn.yinsh.hrm.client.impl;

import cn.yinsh.hrm.client.ESCourseClient;
import cn.yinsh.hrm.domain.ESCourse;
import cn.yinsh.hrm.query.ESCourseQuery;
import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.PageList;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ESCourseClientImpl implements ESCourseClient {
    @Override
    public AjaxResult save(ESCourse courseDocument) {
        return null;
    }

    @Override
    public AjaxResult saveAll(List<ESCourse> courseDocuments) {
        return null;
    }

    @Override
    public AjaxResult delete(Long id) {
        return null;
    }

    @Override
    public PageList<ESCourse> page(ESCourseQuery esCourseQuery) {
        return null;
    }

    @Override
    public AjaxResult deleteAll(List<Long> ids) {
        return null;
    }
}
