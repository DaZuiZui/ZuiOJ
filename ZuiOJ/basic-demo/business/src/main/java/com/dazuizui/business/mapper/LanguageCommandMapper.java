package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.LanguageCommand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
   public List<LanguageCommand> selectByLanguageId(@Param("languageId") long languageId);


   /**
    * 查询指令内容通过语言id
    * Query instruction content by language id
    * @param languageId
    * @return
    */
   public List<String> selectCommandByLanguageIdAndStatus(@Param("languageId") int languageId,@Param("status")int status);



}
