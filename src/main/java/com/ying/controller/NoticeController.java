package com.ying.controller;

import com.google.common.collect.Lists;
import com.ying.constant.BaseResultEnum;
import com.ying.model.Notice;
import com.ying.resp.BaseRespDto;
import com.ying.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * 控制类
 *
 * @author lyz
 */
@RestController
@RequestMapping("/taskInfo")
public class NoticeController {

    @Autowired
    private INoticeService taskInfoService;

    @PostMapping("save")
    public BaseRespDto save(@RequestBody Notice notice) {
        Notice t = taskInfoService.save(notice);
        return new BaseRespDto(BaseResultEnum.SUCCESS, t);
    }

    /**
     * @param taskTitle 搜索标题
     */
    @GetMapping("search")
    public BaseRespDto search(@RequestParam("taskTitle") String taskTitle, @PageableDefault PageRequest pageRequest) {
        Page<Notice> taskInfos = taskInfoService.findByTitle(taskTitle, pageRequest);
        return new BaseRespDto(BaseResultEnum.SUCCESS, Lists.newArrayList(taskInfos));
    }

    @GetMapping("findById")
    public BaseRespDto findById(long id) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, taskInfoService.findById(id));
    }

    @GetMapping("all")
    public BaseRespDto getAll() {
        return new BaseRespDto(BaseResultEnum.SUCCESS, taskInfoService.findAll());
    }
}
