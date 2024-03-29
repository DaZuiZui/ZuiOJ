package com.dazuizui.business.mongodao;

import com.dazuizui.business.domain.QuestionDiscuss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Mongodao 文章dao
 */
public interface ArticleDiscussionRepository extends MongoRepository<QuestionDiscuss,String> {
    /**
     * 通过上级id查询子评论
     * @param parentId
     * @param pageable
     * @return
     */
    Page<QuestionDiscuss> findByParentId(Long parentId, Pageable pageable);

    /**
     * 通过问题主键分页查询
     * @param questionId
     * @param pageable
     * @return
     */
    Page<QuestionDiscuss> findByQuestionId(Long questionId,Pageable pageable);

    /**
     * 通过questionId删除
     * @param questionId
     * @return
     */
    Long deleteByQuestionId(Long questionId);

    /**
     * delete By questionId in questionId
     * @param questionId
     * @return
     */
    Long deleteByQuestionIdIn(List<Long> questionId);
}
