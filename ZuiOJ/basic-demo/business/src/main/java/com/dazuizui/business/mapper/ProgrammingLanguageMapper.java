package com.dazuizui.business.mapper;


import com.dazuizui.basicapi.entry.LanguageCommand;
import com.dazuizui.basicapi.entry.ProgrammingLanguage;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Bryan yang(杨易达)，Gang Liu(刘港)
 * 编程语言业持久化层
 */
@Mapper
public interface ProgrammingLanguageMapper {
    long insertAProgrammingLanguageTable(ProgramBo programBo);

    /**
     * 获取全部语言
     * @return
     */
    public List<ProgrammingLanguage> getAllLanguage();
}
