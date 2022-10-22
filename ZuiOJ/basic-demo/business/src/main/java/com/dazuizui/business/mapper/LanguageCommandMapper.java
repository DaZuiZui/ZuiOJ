package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.LanguageCommand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LanguageCommandMapper {
   /**
    * 根据语言id去查询
    * Query by language id
    * 
    * @param languageId
    * @return
    */
   public List<LanguageCommand> selectByLanguageId(int languageId);
}
