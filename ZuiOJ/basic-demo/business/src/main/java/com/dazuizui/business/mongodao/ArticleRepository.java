package com.dazuizui.business.mongodao;

import com.dazuizui.business.domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article,String> {
}
