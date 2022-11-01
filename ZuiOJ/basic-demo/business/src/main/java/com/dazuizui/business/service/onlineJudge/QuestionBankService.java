package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface QuestionBankService {
    public String postQuestion(@RequestBody QuestionBankBo questionBankBo);
}
