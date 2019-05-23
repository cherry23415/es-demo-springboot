package com.ying;

import com.google.common.collect.Maps;
import com.ying.model.TaskInfo;
import com.ying.repository.TaskInfoRepository;
import com.ying.resp.BaseRespDto;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author lyz
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EsDemoApplication.class)
@RunWith(SpringRunner.class)
public class AbstractBaseTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TaskInfoRepository taskInfoRepository;

    @Test
    public void testIndex() throws Exception {
        Map map = Maps.newHashMap();
        map.put("taskTitle", "测试");
        ResponseEntity<BaseRespDto> re = restTemplate.getForEntity("/taskInfo/search", BaseRespDto.class, map);
        System.out.println(re.getBody().getData());
    }

    @Test
    public void testQuery() throws Exception {
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery());
        List<TaskInfo> taskInfos = elasticsearchTemplate.queryForList(searchQuery, TaskInfo.class);
        System.out.println(taskInfos);
    }

    @Test
    public void testSave() throws Exception {
        TaskInfo t = new TaskInfo();
        t.setId(Long.valueOf(2));
        t.setUserId(Long.valueOf(1));
        t.setTaskTitle("测试下哈哈哈哈什么啊");
        t.setTaskContent("哈哈发测试发斯蒂芬");
        t.setTaskCategory("分类1");
        taskInfoRepository.save(t);
    }
}

