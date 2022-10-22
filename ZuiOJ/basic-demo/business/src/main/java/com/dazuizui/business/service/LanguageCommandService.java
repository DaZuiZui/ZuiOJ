package com.dazuizui.business.service;

import com.dazuizui.basicapi.entry.LanguageCommand;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public interface LanguageCommandService {
    /**
     * 根据语言id去查询
     * Query by language id
     * 
     * @param languageId
     * @return
     */
   public List<LanguageCommand> queryByLanguageId(int languageId);



}
