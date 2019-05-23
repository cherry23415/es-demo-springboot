package com.ying.model;


import com.ying.constant.SearchConstant;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


/**
 * @author lyz
 */
@Data
@Document(indexName = SearchConstant.INDEX_NAME, type = SearchConstant.TYPE_TASK_INFO)
public class TaskInfo {

    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long userId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String taskTitle;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String taskContent;

    @Field(type = FieldType.Keyword)
    private String taskCategory;
}
