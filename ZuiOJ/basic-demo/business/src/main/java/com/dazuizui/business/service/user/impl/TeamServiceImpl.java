package com.dazuizui.business.service.user.impl;

import com.alibaba.excel.EasyExcel;
import com.dazuizui.business.domain.TeamInfo;
import com.dazuizui.business.domain.User;
import com.dazuizui.business.excel.TeamListerner;
import com.dazuizui.business.service.user.TeamService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 比赛队伍业务接口实现
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private UserService userService;

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
    private String generate(List<TeamInfo> list){
        for (int i = 0; i < list.size(); i++) {
            User user = new User();
            user.setCreateTime(new Date());
            user.setDelFlag(0);
            user.setStatus(-1);
            user.setName(list.get(i).getZh_name());
            user.setUsername(UUID.randomUUID().toString());
            user.setPassword(UUID.randomUUID().toString());

            ThreadLocalUtil.DataOfThreadLocal.get().put("teaminfo",list.get(i));
            userService.register(user);
            ThreadLocalUtil.DataOfThreadLocal.remove();
        }
        return null;
    }
}
