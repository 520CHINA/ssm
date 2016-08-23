package com.geaby.aplus.untils;

import org.junit.Test;
import org.springframework.cache.Cache;

import com.geaby.aplus.BaseTest;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;

public class MemcacheTest extends BaseTest {
	
	@Test
	public void testMemcached() throws Exception {
//		MemcachedCacheManager manager = new MemcachedCacheManager();
//		Cache cache = manager.getCache("memcachedCacheClient");
//		cache.put("1001", "test");
//		
//		Object value = null;  
//		value = cache.get("1001");
//		System.out.println(value);
		
		MemcachedClient client=new XMemcachedClient("127.0.0.1",11211);

		//同步存储value到memcached，缓存超时为1小时，3600秒。
		client.set("key",3600,"value");
		//从memcached获取key对应的value
		Object someObject=client.get("key");
		String value = someObject.toString();
		System.out.print(value);

		//从memcached获取key对应的value,操作超时2秒
		someObject=client.get("key",2000);
		
		//更新缓存的超时时间为10秒。
		boolean success=client.touch("key",10);

		//删除value
		client.delete("key");
	}

}
