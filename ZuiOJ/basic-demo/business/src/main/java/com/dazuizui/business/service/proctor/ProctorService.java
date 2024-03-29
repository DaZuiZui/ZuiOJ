package com.dazuizui.business.service.proctor;

import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.domain.bo.AddProctorBo;
import com.dazuizui.business.domain.bo.AdminDeleteProctorByIdBo;
import com.dazuizui.business.domain.bo.AdminGetProctorsByPaginBo;
import com.dazuizui.business.domain.bo.ProctorGetFutureEventsInfoBo;
import com.dazuizui.business.domain.vo.ProctorAnalysisVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    /**
     * 分页获取监考人员
     * @return
     */
    public String adminGetProctorsByPagin(AdminGetProctorsByPaginBo adminGetProctorsByPaginBo);

    /**
     * 管理员删除监考人员通过监考人员Id
     * @param adminDeleteProctorByIdBo
     * @return
     */
    public String adminDeleteProctorByIdOfProctor(AdminDeleteProctorByIdBo adminDeleteProctorByIdBo);
}
