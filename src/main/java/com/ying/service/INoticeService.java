package com.ying.service;

import com.ying.model.Notice;
import com.ying.resp.ListResultDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface INoticeService {
    Optional<Notice> findById(long id);

    Notice save(Notice notice);

    void deleteById(long id);

    List<Notice> findAll();

    ListResultDto<Notice> findByTitleLikeOrContextLike(String context, String title, Pageable pageable);

    ListResultDto<Notice> query(String query, Pageable pageable);

    ListResultDto<Notice> queryHigh(String query, Pageable pageable);
}
