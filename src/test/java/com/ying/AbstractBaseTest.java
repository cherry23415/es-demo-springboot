package com.ying;

import com.ying.model.Notice;
import com.ying.resp.BaseRespDto;
import com.ying.service.INoticeService;
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
    private INoticeService noticeService;

    @Test
    public void testIndex() throws Exception {
        ResponseEntity<BaseRespDto> re = restTemplate.getForEntity("/notice/query?query=测试&page=0&size=0", BaseRespDto.class);
        System.out.println(re.getBody().getData());
    }

    @Test
    public void testQuery() throws Exception {
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery());
        List<Notice> notices = elasticsearchTemplate.queryForList(searchQuery, Notice.class);
        System.out.println(notices);
    }

    @Test
    public void testSave() throws Exception {
        Notice t = new Notice();
        t.setId(Long.valueOf(1));
        t.setTitle("测试下哈哈哈哈什么啊");
        t.setContext("哈哈发测试发斯蒂芬");
        t.setCategory("分类1");
        noticeService.save(t);
    }

    @Test
    public void testDel() throws Exception {
        noticeService.deleteById(1);
    }

}

