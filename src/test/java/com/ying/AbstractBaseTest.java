package com.ying;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lyz
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EsDemoApplication.class)
@RunWith(SpringRunner.class)
public class AbstractBaseTest {
    @Autowired
    private TestRestTemplate restTemplate;
}

