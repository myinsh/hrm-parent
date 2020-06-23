package cn.yinsh.hrm.repository;

import cn.yinsh.hrm.domain.ESCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESCourseRepository extends ElasticsearchRepository<ESCourse,Long> {
}
