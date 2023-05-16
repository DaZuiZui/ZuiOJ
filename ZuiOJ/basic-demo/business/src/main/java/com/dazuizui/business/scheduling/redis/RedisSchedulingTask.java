package com.dazuizui.business.scheduling.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.IOException;


/**
 * @author Dazui
 * Redis定时任务
 */
@Configuration
@EnableScheduling
public class RedisSchedulingTask {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 每天凌晨1点执行一次任务
     * 我们通过 RedisTemplate 获取 Redis 连接工厂 RedisConnectionFactory，然后获取 Redis 连接 RedisConnection。接下来，我们使用 scan 方法进行遍历，以获取所有键。
     * 对于每个键，我们使用 ttl 方法获取其剩余生存时间（TTL），如果剩余生存时间小于等于 0，表示键已过期，我们使用 RedisTemplate 的 delete 方法删除该键。
     * 请注意，代码中的 scanOptions().match("*").count(100) 可以根据需要进行调整。match("*") 表示匹配所有键，count(100) 表示每次遍历返回的键的数量。你可以根据实际情况进行适当的调整。
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void cleanupExpiredKeys() throws IOException {
        RedisConnection connection = redisConnectionFactory.getConnection();

        try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match("*").count(100).build())) {
            while (cursor.hasNext()) {
                byte[] keyBytes = cursor.next();
                String key = new String(keyBytes);

                if (connection.ttl(keyBytes) <= 0) {
                    redisTemplate.delete(key);
                }
            }
        }
    }

}
