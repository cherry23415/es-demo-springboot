package com.ying.controller;

import com.google.common.collect.Lists;
import com.ying.constant.BaseResultEnum;
import com.ying.model.Notice;
import com.ying.resp.BaseRespDto;
import com.ying.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * 控制类
 *
 * @author lyz
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @PostMapping("save")
    public BaseRespDto save(@RequestBody Notice notice) {
        Notice t = noticeService.save(notice);
        return new BaseRespDto(BaseResultEnum.SUCCESS, t);
    }

    /**
     * @param query 标题或内容搜索
     */
    @GetMapping("search")
    public BaseRespDto search(String query, int page, int size) {
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.DESC, "id"));
        Page<Notice> notices1 = noticeService.findByTitle(query, pageable);
        return new BaseRespDto(BaseResultEnum.SUCCESS, Lists.newArrayList(notices1));
    }

    @GetMapping("findById")
    public BaseRespDto findById(long id) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.findById(id));
    }

    @GetMapping("all")
    public BaseRespDto getAll() {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.findAll());
    }
}
