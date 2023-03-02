package com.dazuizui.business.mongodao;

import com.dazuizui.business.domain.QuestionDiscuss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

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
}
