package com.dazuizui.business.service.onlineJudge.impl;

import com.dazuizui.basicapi.entry.LanguageCommand;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageCommandServiceImpl implements LanguageCommandService {
    @Autowired
    private LanguageCommandMapper languageCommandMapper;

    /**
     * 根据语言id去查询
     * Query by language id
     *      
     * @param languageId
     * @return
     */
    @Override
    public List<LanguageCommand> queryByLanguageId(int languageId) {
        return languageCommandMapper.selectByLanguageId(languageId);
    }
}
