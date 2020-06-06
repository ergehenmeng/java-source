package com.eghm;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.Date;
import java.util.List;

public class ZookeeperTest {
    public static void main(String[] args) throws Exception {
        System.setProperty("jute.maxbuffer","4194304");
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("localhost:8090")
                .sessionTimeoutMs(30000)
                .connectionTimeoutMs(10000)
                //超时重试策略
                .retryPolicy(new RetryNTimes(5, 3000))
                .namespace("curator_namespace")
                .build();

        client.start();

        //监听子节点变化
        PathChildrenCache childrenCache = new PathChildrenCache(client, "/curator", true);
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        childrenCache.getListenable().addListener((c, event) -> {
            ChildData data = event.getData();
            System.out.println(" type:" + event.getType() + " path:" + data.getPath());
        });

        //删除节点
        client.delete().forPath("/curator/node");

        //创建节点
        String path = client.create()
                //自动创建不存在的父节点
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                //授权策略
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/curator/node", "node".getBytes());



        //获取节点信息
        Stat stat = new Stat();
        byte[] forPath = client.getData().storingStatIn(stat).forPath("/curator/node");
        System.out.println("getData:" + new String(forPath));
        System.out.println(stat);

        //节点更新
        client.setData().withVersion(stat.getVersion()).forPath("/curator/node","update".getBytes());

        //子节点数据查询
        Stat child = new Stat();
        List<String> list = client.getChildren().storingStatIn(child).forPath("/curator");
        for (String c : list) {
            System.out.println("子节点:" + c);
        }

        //当前节点变化的监听
        NodeCache nodeCache = new NodeCache(client, "/curator");
        nodeCache.start(true);
        nodeCache.getListenable().addListener(() -> {
            ChildData childData = nodeCache.getCurrentData();
            if (childData != null) {
                System.out.println("key:" + childData.getPath() + " value: " + new String(childData.getData()));
            }
        });



        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(path);
    }
}
