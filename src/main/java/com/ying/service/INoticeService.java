package com.ying.service;

import com.ying.model.Notice;
import com.ying.resp.ListResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface INoticeService {
    Optional<Notice> findById(long id);

    Notice save(Notice notice);

    void deleteById(long id);

    List<Notice> findAll();

    ListResultDto<Notice> findByContext(String context, Pageable pageable);

    ListResultDto<Notice> findByTitle(String title, Pageable pageable);

    ListResultDto<Notice> findByTitleLikeOrContextLike(String context, String title, int page, int size);

    ListResultDto<Notice> query(String query, int page, int size);
}
