package com.dazuizui.business.service;

import com.dazuizui.business.mapper.CodeDetailedInContestMapper;
import com.dazuizui.business.mapper.CodeInContestMapper;
import org.bson.types.Code;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QuestionbankServiceImplTest {
    @Autowired
    private CodeDetailedInContestMapper codeDetailedInContestMapper;
    @Autowired
    private CodeInContestMapper codeInContestMapper;

    @Test
    public void findCodeDe(){
        List<Long> list = new ArrayList<>();
        list.add(10122l);
        List<Long> list1 = codeInContestMapper.queryMdTextIdByQuestionId(list);
        System.out.println(list1);
    }
}
