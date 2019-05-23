package com.ying.repository;

import com.ying.model.TaskInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lyz
 */
@Repository
public interface TaskInfoRepository extends ElasticsearchRepository<TaskInfo, Long> {
}
