package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.springframework.stereotype.Service;

/**
 * @author Bryan yang(杨易达)，Gang Liu(刘港)
 * 编程语言业务接口
 */
@Service
public interface ProgrammingLanguageService {
    public long addProgrammingLanguageTable(ProgramBo programBo);
}
