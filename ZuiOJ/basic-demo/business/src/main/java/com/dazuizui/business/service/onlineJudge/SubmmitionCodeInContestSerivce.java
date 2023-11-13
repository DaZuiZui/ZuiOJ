package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.DuplicateCodeBo;
import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface SubmmitionCodeInContestSerivce {
    /**
     * 该业务接口主要给websocket OJ数据大屏使用.
     * 查询当前比赛status所有状态的数量，
     * @param contestId
     * @return
     */
    public ResponseVo findStatusCountsByContestId(@RequestParam("contestId")Long contestId);

    /**
     * 获取涉嫌重复的代码
     * @param duplicateCode
     * @return
     */
    public String getDuplicateCode(DuplicateCodeBo duplicateCode);

    /**
     * 通过比赛id还有题目id还有用户id指定提交代码数据业务接口
     * @param findByContestIdAndQuestionIdAndUserIdBo
     * @return
     */
    public String filterQueryMatchSaveCode( FilterQueryMatchSaveCodeBo findByContestIdAndQuestionIdAndUserIdBo);

    /**
     * 通过id查询代码详细信息
     */
    public String findOneById(@Param("id")Long id);


    /**
     * 通过提交记录id删除记录
     * @param contestId
     * @return
     */
    public String deleteByContestId(Long contestId);

    /**
     * 通过提交记录id删除记录
     * @param id  mongoDb主键id
     * @return
     */
    public String deleteById(String id);
}
