package com.dazuizui.business.service.system.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.service.system.SystemTokenService;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Bryan yang
 *         05/8/2023
 * 该业务接口用于使用服务于系统操作
 */
@Service
public class SystemTokenServiceImpl implements SystemTokenService {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 幂等性验证
     *    如果当前存在幂等性问题则返回true，不存在则返回false
     *
     * @return boolean
     */
    @Override
    public boolean existenceOfIdempotence(String nonPowerToken) {
        //1.防止非幂等性操作
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b) {
            ThreadLocalUtil.mapThreadLocal.get().put("error", "异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return true;
        }

        return false;
    }
}
