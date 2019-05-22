package com.ying.elasticsearch;

import com.ying.constant.SearchConstant;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by lyz on 2016/11/17.
 */
public class EsService {

    @Resource(name = "client")
    private TransportClient client;

    public void insert() {
        try {
            IndexResponse response = client.prepareIndex(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TASK_INFO, "1")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String id) {
        GetResponse response = client.prepareGet(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TASK_INFO, id)
                .setOperationThreaded(false)
                .get();
        return response.getSourceAsString();
    }

    public void update(String id) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TASK_INFO, id)
                    .doc(jsonBuilder()
                            .startObject()
                            .field("gender", "male")
                            .endObject());
            client.update(updateRequest).get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsert() {
        try {
            BulkRequestBuilder bulkRequest = client.prepareBulk();

            // either use client#prepare, or use Requests# to directly build index/delete requests
            bulkRequest.add(client.prepareIndex(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TASK_INFO, "1")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
            );

            bulkRequest.add(client.prepareIndex(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TASK_INFO, "2")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "another post")
                            .endObject()
                    )
            );
            bulkRequest.add(client.prepareIndex(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TASK_INFO, "3")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "cherry")
                            .field("postDate", new Date())
                            .field("message", "cherry another post")
                            .endObject()
                    )
            );
            bulkRequest.add(client.prepareIndex(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TEST, "foo")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("do", "test")
                            .field("postDate", new Date())
                            .field("message", "haha another post")
                            .endObject()
                    )
            );

            BulkResponse bulkResponse = bulkRequest.get();
            if (bulkResponse.hasFailures()) {
                // process failures by iterating through each bulk response item
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> multiGet() {
        List<String> list = new ArrayList<>();
        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TASK_INFO, "1")
                .add(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TASK_INFO, "2", "3", "4")
                .add(SearchConstant.INDEX_NAME, SearchConstant.TYPE_TEST, "foo")
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response != null && response.isExists()) {
                String json = response.getSourceAsString();
                list.add(json);
            }
        }
        return list;
    }
}
