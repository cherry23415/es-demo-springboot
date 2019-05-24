package com.ying.service.impl;

import com.google.common.collect.Lists;
import com.ying.model.Notice;
import com.ying.repository.NoticeRepository;
import com.ying.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Optional<Notice> findById(long id) {
        return noticeRepository.findById(id);
    }

    @Override
    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public void delete(Notice notice) {
        noticeRepository.delete(notice);
    }

    @Override
    public List<Notice> findAll() {
        return Lists.newArrayList(noticeRepository.findAll());
    }

    @Override
    public Page<Notice> findByContext(String context, Pageable pageable) {
        return noticeRepository.findByContext(context, pageable);
    }

    @Override
    public Page<Notice> findByTitle(String title, Pageable pageable) {
        return noticeRepository.findByTitle(title, pageable);
    }
}
