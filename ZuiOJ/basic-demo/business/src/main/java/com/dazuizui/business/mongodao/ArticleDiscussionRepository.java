package com.dazuizui.business.mongodao;

import com.dazuizui.basicapi.entry.mongo.Discuss;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongodao 文章dao
 */
public interface ArticleDiscussionRepository extends MongoRepository<Discuss,Long> {

}
