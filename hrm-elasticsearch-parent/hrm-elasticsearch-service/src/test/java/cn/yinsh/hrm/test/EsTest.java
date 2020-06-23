/*
package cn.yinsh.hrm.test;

import cn.yinsh.hrm.domain.ESCourse;
import cn.yinsh.hrm.repository.ESCourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {
    @Autowired
    private ESCourseRepository repository;


    @Test
    public void testAdd() throws Exception{
        ESCourse esCourse = new ESCourse();
        esCourse.setId(1L);
        esCourse.setName("崔司机");
        esCourse.setUsers("everyOne");
        esCourse.setCourseTypeId(1L);
        esCourse.setGrade(1L);
        esCourse.setPic("/path");
        esCourse.setIntro("什么都没");
        esCourse.setDescription("一下就没得了嘛");
        repository.save(esCourse);
    }
    @Test
    public void testUpdate() throws Exception{
        ESCourse esCourse = new ESCourse();
        esCourse.setId(1L);
        esCourse.setName("崔司机");
        esCourse.setUsers("everyOne");
        esCourse.setCourseTypeId(1L);
        esCourse.setGrade(1L);
        esCourse.setPic("/path");
        esCourse.setIntro("什么都没");
        esCourse.setDescription("崔弈喜欢开车");
        repository.save(esCourse);
    }

    @Test
    public void testAddBatch() throws Exception{
        ESCourse esCourse = new ESCourse();
        esCourse.setId(1L);
        esCourse.setName("崔司机");
        esCourse.setUsers("everyOne");
        esCourse.setCourseTypeId(1L);
        esCourse.setGrade(1L);
        esCourse.setPic("/path");
        esCourse.setIntro("什么都没");
        esCourse.setDescription("jjjjj");

        ESCourse esCourse2 = new ESCourse();
        esCourse2.setId(2L);
        esCourse2.setName("崔弈");
        esCourse2.setUsers("onebyone");
        esCourse2.setCourseTypeId(2L);
        esCourse2.setGrade(2L);
        esCourse2.setPic("/laji");
        esCourse2.setIntro("哈哈哈哈哈");
        esCourse2.setDescription("哟哦哟哟");

        List<ESCourse> courses = Arrays.asList(esCourse,esCourse2);

        repository.saveAll(courses);
    }

    @Test
    public void testFind() throws Exception{

        Optional<ESCourse> byId = repository.findById(1L);
        System.out.println(byId.get());
    }
    @Test
    public void testFindAll() throws Exception{

        Iterable<ESCourse> all = repository.findAll();
        all.forEach(System.out::println);

    }


    @Test
    public void test() throws Exception{
    }
}
*/
