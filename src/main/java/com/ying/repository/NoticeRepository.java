package com.ying.repository;

import com.ying.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lyz
 */
@Repository
public interface NoticeRepository extends ElasticsearchRepository<Notice, Long> {
    Page<Notice> findByContext(String context, PageRequest pageRequest);

    Page<Notice> findByTitle(String title, PageRequest pageRequest);
}
