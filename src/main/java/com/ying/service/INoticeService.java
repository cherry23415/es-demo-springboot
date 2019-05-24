package com.ying.service;

import com.ying.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface INoticeService {
    Optional<Notice> findById(long id);

    Notice save(Notice notice);

    void deleteById(long id);

    List<Notice> findAll();

    Page<Notice> findByContext(String context, Pageable pageable);

    Page<Notice> findByTitle(String title, Pageable pageable);
}
