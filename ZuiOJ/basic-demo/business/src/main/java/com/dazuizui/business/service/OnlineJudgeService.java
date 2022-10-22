package com.dazuizui.business.service;

import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.springframework.stereotype.Service;

/**
 * @author Bryan yang(杨易达)
 * 代码判断业务接口
 */
@Service
public interface OnlineJudgeService {
    /**
     * 判决代码
     * @param programBo
     * @return
     */
    public String judgeTheProgram(ProgramBo programBo);
}
