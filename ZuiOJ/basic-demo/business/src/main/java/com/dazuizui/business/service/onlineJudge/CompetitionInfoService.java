package com.dazuizui.business.service.onlineJudge;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface CompetitionInfoService {
    public String viewRanking(@RequestParam("contestId")Long contestId,Integer page,Integer numbers);
}
