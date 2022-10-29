package com.dazuizui.business.service.onlineJudge.impl;

import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.mapper.ProgrammingLanguageMapper;
import com.dazuizui.business.service.onlineJudge.ProgrammingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bryan yang(杨易达)，Gang Liu(刘港)
 * 编程语言业务接口实现类
 */
@Service
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {
    @Autowired
    private ProgrammingLanguageMapper programmingLanguageMapper;
    @Override
    public long addProgrammingLanguageTable(ProgramBo programBo) {
        return programmingLanguageMapper.insertAProgrammingLanguageTable(programBo);
    }
}
