package com.lewis.sprinbootvue.biz.mybatis.dao.snailReader;

import com.lewis.sprinbootvue.biz.mybatis.entity.BookAriticle;

import java.util.List;

public interface BookArticleMapper {

    List<BookAriticle> queryAllBookArticle();
}
