package com.dazuizui.business.service.user.impl;

import com.alibaba.excel.EasyExcel;
import com.dazuizui.business.domain.TeamInfo;
import com.dazuizui.business.domain.User;
import com.dazuizui.business.domain.bo.AdminAddCompetitionInfoBo;
import com.dazuizui.business.excel.TeamListerner;
import com.dazuizui.business.mapper.TeamInfoMapper;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import com.dazuizui.business.service.user.TeamService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 比赛队伍业务接口实现
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private UserService userService;
    @Autowired
    private TeamInfoMapper teamInfoMapper;
    @Autowired
    private CompetitionInfoService competitionInfoService;
    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 创建比赛账号
     *     通过Excel解析出来参赛者账号的名字 or 团队的名字，还有参赛者人员然后通过系统去生成账号密码
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String generateGameAccountByExcel(MultipartFile file,Long contestId) throws IOException {
        TeamListerner bedListerner = new TeamListerner();
        EasyExcel.read(file.getInputStream(), TeamInfo.class,bedListerner).sheet().doRead();
        List<TeamInfo> list = bedListerner.list;
        this.generate(list,contestId);
        return null;
    }

    @Override
    public String generateGameAccountByDB() {
        return null;
    }

    /**
     * 生成比赛账号
     * @param list
     * @return
     */
    private String generate(List<TeamInfo> list,Long contestId){

        TransactionStatus transactionStatus =  transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        Map<String, Object> userinfo = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");

        for (int i = 0; i < list.size(); i++) {
            User user = new User();
            user.setCreateTime(new Date());
            user.setDelFlag(0);
            user.setStatus(-1);
            user.setName(list.get(i).getZhTeamname());
            user.setUsername(UUID.randomUUID().toString());
            user.setPassword(UUID.randomUUID().toString());
            user.setCreateById(Long.valueOf(String.valueOf(userinfo.get("id"))));

            ThreadLocalUtil.DataOfThreadLocal.get().put("teaminfo",list.get(i));
            //System.err.println(ThreadLocalUtil.DataOfThreadLocal.get().get("teaminfo"));
            ThreadLocalUtil.DataOfThreadLocal.get().put("contestId",contestId);

            try {
                list.get(i).setTeamId(user.getId());
                list.get(i).setStatus(0);
                userService.register(user);

                Long aLong = teamInfoMapper.insertTeamInfo(list.get(i));
                if (aLong == 0){
                    transactionUtils.rollback(transactionStatus);
                }
                //写入报名比赛信息
                AdminAddCompetitionInfoBo adminAddCompetitionInfoBo = new AdminAddCompetitionInfoBo();
                adminAddCompetitionInfoBo.setContestId(contestId);
                adminAddCompetitionInfoBo.setUsername(user.getUsername());
                competitionInfoService.adminAddCompetitionInfo(adminAddCompetitionInfoBo);
            } catch (Exception e) {
                String message = e.getMessage();
                list.get(i).setRemark(message);
                list.get(i).setStatus(3);
                teamInfoMapper.insertTeamInfo(list.get(i));
                transactionUtils.rollback(transactionStatus);
            }

            ThreadLocalUtil.DataOfThreadLocal.remove();
        }

        transactionUtils.commit(transactionStatus);

        return null;
    }
}
