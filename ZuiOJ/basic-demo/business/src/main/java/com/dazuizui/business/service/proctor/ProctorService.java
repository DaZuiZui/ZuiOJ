package com.dazuizui.business.service.proctor;

import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.domain.bo.AddProctorBo;
import com.dazuizui.business.domain.bo.ProctorGetFutureEventsInfoBo;
import org.apache.ibatis.annotations.Param;

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
     * 通过userid 查询一个监考人员
     */
    public Proctor findByIdLimit1(@Param("userId")Long userId);
}
