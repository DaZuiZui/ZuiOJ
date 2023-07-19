package com.dazuizui.business.service.user;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 比赛队伍业务接口
 */
@Service
public interface TeamService {

    /**
     * 创建比赛账号 by Excel
     * @param file
     * @return
     * @throws IOException
     */
    public String generateGameAccountByExcel(MultipartFile file,Long contestId) throws IOException;

    /**
     * 生成比赛账号通过数据库
     * @return
     */
    public String generateGameAccountByDB();
}
