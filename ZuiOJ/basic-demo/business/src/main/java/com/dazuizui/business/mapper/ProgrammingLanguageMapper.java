package com.dazuizui.business.mapper;


import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Bryan yang(杨易达)，Gang Liu(刘港)
 * 编程语言业持久化层
 */
@Mapper
public interface ProgrammingLanguageMapper {
    long insertAProgrammingLanguageTable(ProgramBo programBo);
}
