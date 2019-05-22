package com.ying.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ying.constant.SearchConstant;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;


/**
 * @author lyz
 */
@Data
@Document(indexName = SearchConstant.INDEX_NAME, type = SearchConstant.TYPE_TASK_INFO)
public class TaskInfo {

    @JsonProperty
    private Long taskId;

    @JsonProperty
    private Integer userId;

    @JsonProperty
    private String taskContent;

    @JsonProperty
    private String taskArea;

    @JsonProperty
    private String taskTags;

    @JsonProperty
    private Integer taskState;

    @JsonProperty
    private String updateTime;

    @JsonProperty
    private String userNickName;
}
