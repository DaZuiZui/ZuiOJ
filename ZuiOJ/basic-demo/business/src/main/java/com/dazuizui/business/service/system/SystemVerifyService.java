package com.dazuizui.business.service.system;

import org.springframework.stereotype.Service;

/**
 * 系统验证接口
 */
@Service
public interface SystemVerifyService {

    /**
     * @author Byran yang(Dazui)
     *
     * 校验用户是否登入且是否存在幂等性问题
     * @param nonPowerToken
     * @param token
     * @return
     */
    public boolean veryfiUserAndNonPowerToken(String nonPowerToken,String token);

    /**
     * 查看是否登入
     * 如果登入了就返回true，否则就返回false
     * @param token 用户token
     * @return
     */
    public boolean veryfiUser(String token);

    /**
     * 通过token获取userid
     * @param token
     * @return
     */
    public boolean getUserIdByToken(String token);
    /**
     * 验证监考人员
     * 如果是监考人员就返回true，否则返回false
     * @return boolean
     */
    public boolean veryfiProctor(String token);

    /**
     * 验证此用户是否为在指定比赛的考官
     * @param token
     * @return
     */
    public boolean veryfiProctorInContest(String token,Long contestId);

    /**
     * 验证是否为管理员
     * 如果是管理员就返回true，否则就返回false
     * @param token
     * @param leave 权限等级
     * @return
     */
    public boolean veryfiAdmin(String token,Integer leave);

    /**
     * 解决接口幂等性的问题
     * @param nonPowerToken
     * @return
     */
    public boolean verfiNonPowerToken(String nonPowerToken);

    /**
     * 鉴权用户权限和接口幂等性问题
     * @param nonPowerToken
     * @param token
     * @return
     */
    public boolean verfiNonPowerTokenAndAdminToken(String nonPowerToken,String token,Integer leave);


    /**
     * 查看是否为自己的文章
     * @param token
     * @param id
     * @return
     */
    public boolean isMyArticle(String token,Long id);

    /**
     * 鉴权是否为管理员或者该内容的主人
     * @param token
     * @param leave
     * @return
     */
    public boolean isOwnerOrAdmin(String token,Integer leave,Long id);
}
