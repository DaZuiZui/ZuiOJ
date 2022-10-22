package com.dazuizui.business.service;

import com.dazuizui.basicapi.entry.LanguageCommand;
import org.springframework.stereotype.Service;

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
