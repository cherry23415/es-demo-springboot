package com.ying.client;

import com.ying.config.ConfigUtil;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取客户端
 * <p/>
 * Created by lyz on 2016/11/16.
 */
@Configuration
public class ClientFactoryBean {

    //注入的ElasticSearch实例
    @Bean(name = "client")
    public Client getESClient() {
        TransportClient client = null;
        try {
            int port = Integer.parseInt(ConfigUtil.getValue("es_cluster_port"));
            String ips = ConfigUtil.getValue("es_cluster_clients");
            String clusterName = ConfigUtil.getValue("es_cluster_name");

            //设置集群名字
            Settings settings = Settings.builder().put("client.transport.sniff", true)
                    .put("cluster.name", clusterName).build();

            client = new PreBuiltTransportClient(settings);

            //读取的ip列表是以逗号分隔的
            for (String ip : ips.split(",")) {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
            if (client != null)
                client.close();
        }
        return client;
    }

}
