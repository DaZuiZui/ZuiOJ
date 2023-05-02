package com.dazuizui.business.service.proctor;

import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.domain.bo.AddProctorBo;
import com.dazuizui.business.domain.bo.ProctorGetFutureEventsInfoBo;
import com.dazuizui.business.domain.vo.ProctorAnalysisVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProctorService {
    /**
     * 添加监考信息
     * @param addProctorBo
     * @return
     */
    public String addProctor(AddProctorBo addProctorBo);

    /**
     * 通过userid和contestid查询差事
     * @param contestId
     * @param userId
     * @return
     */
    public Proctor findByContestIdAndUserId(Long contestId,Long userId);

    /**
     * 分页获取现在进行时和未来进行时的数据
     * @param proctorGetFutureEventsInfoBo
     * @return
     */
    public String proctorGetFutureEventsInfo(ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo);

    /**
     * 分页获取过去时的数据
     * @param proctorGetFutureEventsInfoBo
     * @return
     */
    public String proctorGetLastEventsInfo(ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo);

    /**
     * 通过userid 查询一个监考人员
     */
    public Proctor findByIdLimit1(@Param("userId")Long userId);

    /**
     * 解析监考身份
     * @param token
     * @return
     */
    public String proctorAnalysis(String token);
}
