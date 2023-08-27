package com.dazuizui.business.mongodao;

import com.dazuizui.business.domain.CodeInContest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongodao 比赛时提交的代码dao
 */
public interface SubmmitionCodeInContestRepository extends MongoRepository<CodeInContest,String> {


    /**
     * 通过比赛id还有题目id还有用户id指定提交代码数据
     * @param contestId
     * @param questionId
     * @param userId
     * @param pageable
     * @return
     */
    Page<CodeInContest> findByContestIdAndQuestionIdAndUserIdAndDelFlag(Long contestId,Long questionId,Long userId,Integer delFlag, Pageable pageable);

    /**
     * 通过id删除删除
     * @param s
     */
    @Override
    void deleteById(String s);

    /**
     * 通过比赛id删除
     * @param contestId
     */
    void deleteByContestId(Long contestId);

    /**
     * 查看该用户本题是否有2个及以上的ac记录
     */
    Page<CodeInContest> findByContestIdAndQuestionIdAndUserIdAndDelFlagAndStatus(Long contestId,Long questionId,Long userId,Integer delFlag,Integer status, Pageable pageable);


    /**
     * 通过题号和状态查询
     * @param questionId
     * @param status
     * @return
     */
    Page<CodeInContest> findByQuestionIdAndStatus(Long questionId,Integer status, Pageable pageable);
}
