package com.dazuizui.business.service.system;

import org.springframework.stereotype.Service;

/**
 * @author Bryan yang
 *         05/8/2023
 * 该业务接口用于使用服务于系统操作
 */
@Service
public interface SystemTokenService {
    /**
     * 幂等性验证
     *    如果当前存在幂等性问题则返回true，不存在则返回false
     *
     * @return boolean
     */
    public boolean existenceOfIdempotence(String token);
}
