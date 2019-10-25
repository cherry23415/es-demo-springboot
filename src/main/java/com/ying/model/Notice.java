package com.ying.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author lyz
 */
@Data
@Document(indexName = "ying", type = "notice")
public class Notice {

    @Id
    private Long id;

    @ApiModelProperty("标题")
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @ApiModelProperty("内容")
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String context;

    @ApiModelProperty("分类")
    @Field(type = FieldType.Keyword)
    private String category;
}
