package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.AcContestQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * AC比赛题目实体类
 */
@Mapper
public interface AcContestQuestionMapper {
    /**
     * 通过比赛题目
     * @param acContestQuestion
     * @return
     */
    public Long acTheQuestionInDB(@Param("acContestQuestion") AcContestQuestion acContestQuestion,@Param("status") int status);

    /**
     * 增加提交量
     * @param acContestQuestion
     * @return
     */
    public Long recordSubmissions(@Param("acContestQuestion") AcContestQuestion acContestQuestion,@Param("status") int status);


    /**
     * 检查提交是否存在
     * @param acContestQuestion
     * @return
     */
    public AcContestQuestion checkSubmissions(AcContestQuestion acContestQuestion);


}
