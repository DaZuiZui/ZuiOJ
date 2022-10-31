package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.ConTest;
import org.springframework.stereotype.Service;


/**
 * @author yida yang
 * 竞赛模块接口实现类
 */
@Service
public interface ConTestSerivce {
    public String postContest(ConTest conTest);
}
