package com.ying.service.impl;

import com.google.common.collect.Lists;
import com.ying.es.MyResultMapper;
import com.ying.model.Notice;
import com.ying.repository.NoticeRepository;
import com.ying.resp.ListResultDto;
import com.ying.service.INoticeService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lyz
 */
@Service
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private MyResultMapper myResultMapper;

    @Override
    public Optional<Notice> findById(long id) {
        return noticeRepository.findById(id);
    }

    @Override
    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public void deleteById(long id) {
        noticeRepository.deleteById(id);
    }

    @Override
    public List<Notice> findAll() {
        return Lists.newArrayList(noticeRepository.findAll());
    }

    private ListResultDto<Notice> pageToList(Page<Notice> notices) {
        ListResultDto<Notice> listResultDto = new ListResultDto<>();
        listResultDto.setDatas(notices.getContent());
        listResultDto.setCount(notices.getTotalElements());
        listResultDto.setPage(notices.getNumber());
        listResultDto.setSize(notices.getSize());
        return listResultDto;
    }

    @Override
    public ListResultDto<Notice> findByTitleLikeOrContextLike(String context, String title, Pageable pageable) {
        Page<Notice> notices = noticeRepository.findByTitleLikeOrContextLike(context, title, pageable);
        return pageToList(notices);
    }

    @Override
    public ListResultDto<Notice> query(String query, Pageable pageable) {
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(query, "title", "context");
        return pageToList(noticeRepository.search(queryBuilder, pageable));
    }

    @Override
    public ListResultDto<Notice> queryHigh(String query, Pageable pageable) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(query, "title", "context"))
                .withHighlightFields(new HighlightBuilder.Field("*").preTags("<span style=\"color:red\">").postTags("</span>"))
                .withPageable(pageable)
                .build();
        Page<Notice> notices = elasticsearchTemplate.queryForPage(searchQuery, Notice.class, myResultMapper);
        return pageToList(notices);
    }
}