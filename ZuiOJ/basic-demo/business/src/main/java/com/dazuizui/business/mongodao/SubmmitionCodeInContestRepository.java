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
    Page<CodeInContest> findByContestIdAndQuestionIdAndUserId(Long contestId,Long questionId,Long userId, Pageable pageable);
}
