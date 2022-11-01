package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.CompetitionQuestionBank;
import org.springframework.stereotype.Service;

@Service
public interface CompetitionQuestionBankService {
    /**
     * 添加题在比赛题库中
     * @return
     */
    public String addQuestionInContest(CompetitionQuestionBank competitionQuestionBank);
}
