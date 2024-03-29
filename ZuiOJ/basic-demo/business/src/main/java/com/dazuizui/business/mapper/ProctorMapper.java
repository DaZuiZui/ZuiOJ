package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.domain.ProctorInfo;
import com.dazuizui.business.domain.bo.AdminGetProctorsByPaginBo;
import com.dazuizui.business.domain.bo.ProctorGetFutureEventsInfoBo;
import com.dazuizui.business.domain.bo.ProctorGetLastEventsInfoBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface ProctorMapper {
    /**
     * 添加监考信息
     * @param proctor
     * @return
     */
    public Long addProctor(Proctor proctor);

    /**
     * 检测是否为当前考场的监考人员
     * @param contestId
     * @param userId
     * @return
     */
    public Proctor findByContestIdAndUserId(@Param("contestId") Long contestId,@Param("userId") Long userId);

    /**
     * 获取当前监考员角色可以监考的未来和现在进行时的差事
     * @param proctorGetFutureEventsInfoBo
     * @return
     */
    public List<Contest>  proctorGetFutureEvents(@RequestParam("proctorGetFutureEventsInfoBo") ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo,@RequestParam("userId")Long userId);


    /**
     * 获取当前监考员角色可以监考的未来和现在进行时的差事数量
     * @param userId
     * @return
     */
    public Long proctorGetFutureEventsNumber(@Param("userId")Long userId);


    /**
     * 获取当前监考员角色可以监考过去时的差事
     * @param proctorGetFutureEventsInfoBo
     * @return
     */
    public List<Contest>  proctorGetLastEvents(@RequestParam("proctorGetFutureEventsInfoBo") ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo, @RequestParam("userId")Long userId);


    /**
     * 获取当前监考员角色可以监考过去时的差事数量
     * @param userId
     * @return
     */
    public Long proctorGetLastEventsNumber(@Param("userId")Long userId);

    /**
     * 通过userid 查询一个监考人员
     */
    public Proctor findByIdLimit1(@Param("userId")Long userId);

    /**
     * 获取当前竞赛的监考人员个数
     * @param contestId
     * @return
     */
    public Long getTheNumberOfProctorForThisCOmpetition(@Param("contestId")Long contestId);

    /**
     * 分页获取监考人员
     * @param adminGetProctorsByPaginBo
     * @return
     */
    public List<ProctorInfo> getProctorsByPagin(AdminGetProctorsByPaginBo adminGetProctorsByPaginBo);

    /**
     * 通过id删除
     * @param id
     * @return Long.class
     */
    public Long deleteById(@Param("id")Long id);
}
