package com.dazuizui.business.mapper.student;

import com.dazuizui.basicapi.entry.Universty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 大学Mapper
 */
@Mapper
public interface UniverstyMapper {
    /**
     * 查询所有大学
     *     该数据量极少不考虑优化暂时，后续再说，加入todo
     * @return List<Universty>
     */
    public List<Universty> queryAllUniversty();

    /**
     * 获取大学信息
     * @return
     */
    public Universty queryUniverstryByUniverstyId(@Param("id") Long id);
}
