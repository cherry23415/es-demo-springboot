package com.ying.controller;

import com.ying.constant.BaseResultEnum;
import com.ying.model.Notice;
import com.ying.resp.BaseRespDto;
import com.ying.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param query 全文搜索
     */
    @GetMapping("search")
    public BaseRespDto search(String query, int page, int size) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.findByTitleLikeOrContextLike(query, query, page, size));
    }


    /**
     * @param query 全文搜索
     */
    @GetMapping("search2")
    public BaseRespDto search2(String query, int page, int size) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.query(query, page, size));
    }

    @GetMapping("findById")
    public BaseRespDto findById(long id) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.findById(id));
    }

    @GetMapping("all")
    public BaseRespDto getAll() {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.findAll());
    }

    @GetMapping("delById")
    public BaseRespDto delById(long id) {
        noticeService.deleteById(id);
        return new BaseRespDto(BaseResultEnum.SUCCESS);
    }
}
