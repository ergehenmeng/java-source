package com.eghm.redis;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.checkerframework.checker.units.qual.K;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;

import java.util.*;

public class RedisStream {

	private static final String KEY = "key";

	private static final String GROUP = "group";

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		//jedis.xgroupCreate(KEY, GROUP, new StreamEntryID(), true);
		producer();
		new Thread(() -> customer()).start();

		List<String> list = Lists.newArrayList();



	}

	public static void customer() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		while (true) {
			AbstractMap.SimpleImmutableEntry<String, StreamEntryID> entry = new AbstractMap.SimpleImmutableEntry<>(KEY, StreamEntryID.UNRECEIVED_ENTRY);
			List<Map.Entry<String, List<StreamEntry>>> group = jedis.xreadGroup(GROUP, "C1", 1, 120 * 1000, true, entry);
			if (group != null && group.size() == 1) {
				// 读取到消息
				Map<String, String> content = group.get(0).getValue().get(0).getFields();
				System.out.println("Consumer 1 读取到消息 ID：" + group.get(0).getValue().get(0).getID() +
						" 内容：" + new Gson().toJson(content));
			}
		}
	}

	public static void producer() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		Map<String, String> map = new HashMap<>();
		map.put("data", "redis");
		StreamEntryID entryId = jedis.xadd(KEY, null, map);
		System.out.println("消息添加成功" + entryId);
	}

}
