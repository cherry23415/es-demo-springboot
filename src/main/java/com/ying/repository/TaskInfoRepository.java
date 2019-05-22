package com.ying.repository;

import com.ying.model.TaskInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author lyz
 */
@Component
public interface TaskInfoRepository extends ElasticsearchRepository<TaskInfo, Long> {
}
