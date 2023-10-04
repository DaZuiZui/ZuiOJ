package com.dazuizui.business.service.invigilator.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.business.domain.RedisKey;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.Invigilator;
import com.dazuizui.business.domain.bo.FindByUserIdAndStatusAndDelFlagBo;
import com.dazuizui.business.mapper.InvigilatorMapper;
import com.dazuizui.business.service.invigilator.InvigilatorService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 监考业务实现类
 * 该类和Proctor为同一业务类，因为设计出现的问题，以后慢慢优化
 */
@Service
public class InvigilatorServiceImpl implements InvigilatorService {
    @Autowired
    private InvigilatorMapper invigilatorMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TransactionUtils transactionUtils;
    /**
     * 添加面试者
     * @param invigilator
     * @return
     */
    @Override
    public String addInvigilator(Invigilator invigilator) {

        return null;
    }

    /**
     * 通过userId查询数据
     * @return
     */
    @Override
    public String findByUserIdAndStatusAndDelFlag(FindByUserIdAndStatusAndDelFlagBo findByUserIdAndStatusAndDelFlagBo){
        //从Redis获取数据
        Invigilator invigilatorInDB = (Invigilator) redisUtil.getStringInRedis(RedisKey.ZuiBlogInvigilatorUserId+findByUserIdAndStatusAndDelFlagBo.getUserId());

        //如果redis没有获取到就从mysql获取
        if (invigilatorInDB == null){
            invigilatorInDB = invigilatorMapper.findByUserIdAndStatusAndDelFlag(findByUserIdAndStatusAndDelFlagBo);
            //权限不足，该用户不为面试者
            if (invigilatorInDB == null){
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.insufficientPermissions,null, StatusCode.insufficientPermissions));
            }

            //存入reids
            redisUtil.setStringInRedis(RedisKey.ZuiBlogInvigilatorUserId+findByUserIdAndStatusAndDelFlagBo.getUserId(),RedisKey.OutTime,invigilatorInDB);

        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.insufficientPermissions,null, StatusCode.insufficientPermissions));
    }

    /**
     * 通过id删除面试官
     * @param id
     * @return
     */
    @Override
    public String deleteById(Long id) {
        //删除redis缓存
        redisUtil.deleteKey(RedisKey.ZuiBlogInvigilatorUserId + id);
        //删除mysql
        Long numbersOfOpertions = invigilatorMapper.deleteById(id);

        if (numbersOfOpertions.longValue() == 0l){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
