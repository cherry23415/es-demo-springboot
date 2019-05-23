package com.ying.service;

import com.ying.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface INoticeService {
    Optional<Notice> findById(long id);

    Notice save(Notice notice);

    void delete(Notice notice);

    List<Notice> findAll();

    Page<Notice> findByContext(String context, PageRequest pageRequest);

    Page<Notice> findByTitle(String title, PageRequest pageRequest);
}
