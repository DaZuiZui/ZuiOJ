package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.AcContestQuestion;

import java.io.Serializable;
import java.util.List;

public class GetContestDateVo implements Serializable {
     //通过日志
     private List<AcContestQuestion> acContestQuestions;

     public List<AcContestQuestion> getAcContestQuestions() {
          return acContestQuestions;
     }

     public void setAcContestQuestions(List<AcContestQuestion> acContestQuestions) {
          this.acContestQuestions = acContestQuestions;
     }
}
