package com.ying.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ying.constant.SearchConstant;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


/**
 * @author lyz
 */
@Data
@Document(indexName = SearchConstant.INDEX_NAME, type = SearchConstant.TYPE_TASK_INFO)
public class TaskInfo {

    @Id
    @JsonProperty
    private Long taskId;

    @JsonProperty
    private Integer userId;

    @JsonProperty
    private String taskTitle;

    @JsonProperty
    private String taskContent;
}
