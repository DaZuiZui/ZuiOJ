package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public interface OnlineJudgeAop {

    /**
     * 判决代码
     * @return
     */
    public String judgeTheProgram(JoinPoint joinpoint) throws Exception;
}
