package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.Contest;
import org.springframework.stereotype.Service;


/**
 * @author yida yang
 * 竞赛模块接口实现类
 */
@Service
public interface ContestSerivce {
    /**
     * 提交赛制
     * @param conTest
     * @return
     */
    public String postContest(Contest conTest);

    /**
     * 获取未来赛制
     * @return
     */
    public String getFutureEvents();


    /**
     * 获取全部赛制
     * @return
     */
    public String getAllEvents();
}
