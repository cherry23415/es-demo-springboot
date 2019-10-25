package com.ying.controller;

import com.ying.constant.BaseResultEnum;
import com.ying.model.Notice;
import com.ying.resp.BaseRespDto;
import com.ying.service.INoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * 控制类
 *
 * @author lyz
 */
@Api(tags = "notice")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @ApiOperation("添加")
    @PostMapping("save")
    public BaseRespDto save(@RequestBody Notice notice) {
        Notice t = noticeService.save(notice);
        return new BaseRespDto(BaseResultEnum.SUCCESS, t);
    }

    /**
     * @param query 根据内容或标题搜索（不支持带空格的搜索）
     */
    @ApiOperation("根据内容或标题搜索")
    @GetMapping("query")
    public BaseRespDto query(String query, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.findByTitleLikeOrContextLike(query, query, pageable));
    }

    /**
     * @param query 全文搜索（支持带空格搜索，空格搜索时前端转成%20）
     */
    @ApiOperation("全文检索")
    @GetMapping("search")
    public BaseRespDto search(String query, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.query(query, pageable));
    }

    /**
     * @param query 全文搜索（支持带空格搜索，空格搜索时前端转成%20，高亮显示搜索关键字）
     */
    @ApiOperation("全文检索高亮")
    @GetMapping("searchHigh")
    public BaseRespDto searchHigh(String query, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.queryHigh(query, pageable));
    }

    @ApiOperation("根据ID查询")
    @GetMapping("findById")
    public BaseRespDto findById(long id) {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.findById(id));
    }

    @ApiOperation("查询所有")
    @GetMapping("all")
    public BaseRespDto getAll() {
        return new BaseRespDto(BaseResultEnum.SUCCESS, noticeService.findAll());
    }

    @ApiOperation("删除")
    @GetMapping("delById")
    public BaseRespDto delById(long id) {
        noticeService.deleteById(id);
        return new BaseRespDto(BaseResultEnum.SUCCESS);
    }
}
