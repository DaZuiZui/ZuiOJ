package com.dazuizui.business.mongodao;

import com.dazuizui.business.domain.CodeInContest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongodao 比赛时提交的代码dao
 */
public interface SubmmitionCodeInContestRepository extends MongoRepository<CodeInContest,String> {

}
