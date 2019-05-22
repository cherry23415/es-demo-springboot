package com.ying.controller;

import com.ying.constant.BaseResultEnum;
import com.ying.model.TaskInfo;
import com.ying.repository.TaskInfoRepository;
import com.ying.resp.BaseRespDto;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制类
 *
 * @author lyz
 */
@RestController
public class TaskInfoController {

    @Autowired
    private TaskInfoRepository taskInfoRepository;

    @PostMapping("save")
    public BaseRespDto save(@RequestBody TaskInfo taskInfo) {
        taskInfoRepository.save(taskInfo);
        return new BaseRespDto(BaseResultEnum.SUCCESS);
    }


    /**
     * @param taskTitle 搜索标题
     * @param pageable  page = 第几页参数, value = 每页显示条数
     */
    @GetMapping("search")
    public BaseRespDto search(String taskTitle, @PageableDefault(page = 1, value = 10) Pageable pageable) {

        //按标题进行搜索
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("taskTitle", taskTitle);

        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Iterable<TaskInfo> listIt = taskInfoRepository.search(queryBuilder, pageable);
        return new BaseRespDto(BaseResultEnum.SUCCESS, listIt);
    }
}
