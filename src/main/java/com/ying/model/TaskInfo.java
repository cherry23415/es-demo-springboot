package com.ying.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ying.constant.SearchConstant;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


/**
 * @author lyz
 */
@Data
@Document(indexName = SearchConstant.INDEX_NAME, type = SearchConstant.TYPE_TASK_INFO)
public class TaskInfo {

    @Id
    private Long taskId;

    private Integer userId;

    @Field(searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
    private String taskTitle;

    @Field(searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
    private String taskContent;
}
