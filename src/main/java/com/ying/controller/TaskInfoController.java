package com.ying.controller;

import com.ying.constant.BaseResultEnum;
import com.ying.model.TaskInfo;
import com.ying.repository.TaskInfoRepository;
import com.ying.resp.BaseRespDto;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * 控制类
 *
 * @author lyz
 */
@RestController
@RequestMapping("/taskInfo")
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
     */
    @GetMapping("search")
    public BaseRespDto search(@RequestParam("taskTitle") String taskTitle) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("taskTitle", taskTitle);
        Pageable pageable = PageRequest.of(1, 10);
        Iterable<TaskInfo> listIt = taskInfoRepository.search(queryBuilder, pageable);
        return new BaseRespDto(BaseResultEnum.SUCCESS, listIt);
    }

    @GetMapping("find")
    public BaseRespDto findById(Long id) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, taskInfoRepository.findById(id));
    }

    @GetMapping("all")
    public BaseRespDto getAll() {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        return new BaseRespDto(BaseResultEnum.SUCCESS, taskInfoRepository.search(queryBuilder));
    }
}
