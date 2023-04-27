package com.dazuizui.business.service.system;

import org.springframework.stereotype.Service;

/**
 * 系统验证接口
 */
@Service
public interface SystemVerifyService {
    /**
     * 验证监考人员
     * 如果是监考人员就返回true，否则返回false
     * @return boolean
     */
    public boolean veryfiProctor(String token);

    /**
     * 验证此用户是否为在指定比赛的考官
     * @param token
     * @return
     */
    public boolean veryfiProctorInContest(String token,Long contestId);
}
