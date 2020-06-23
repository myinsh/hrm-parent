package cn.yinsh.hrm.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "hrm")
public class ESCourse {
    private Long id;
    private String name;
    private String users;
    private Long courseTypeId;
    private String intro;
    private String description;
    private String all;
    private Long startTime;
    private Long grade;
/*
    @Id
    private Long id;
    @Field(type = FieldType.Keyword,store = true)
    private String name;
    @Field(type = FieldType.Keyword,store = true)
    private String users;
    @Field(type = FieldType.Long)
    private Long courseTypeId;
    @Field(type = FieldType.Keyword,index = false,store = true)
    private String intro;
    @Field(type = FieldType.Keyword,index = false,store = true)
    private String description;
    @Field(type = FieldType.Text)
    private String all;
    @Field(type = FieldType.Long,store = true)
    private Long startTime;
    @Field(type = FieldType.Long,store = true)
    private Long grade;
*/


}
