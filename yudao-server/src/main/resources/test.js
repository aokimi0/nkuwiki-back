package com.ruoyi.system.service.impl;

import com.ruoyi.system.vo.ArticleAuthorDetailVO;
import com.ruoyi.system.mapper.ArticleMapper;
import com.ruoyi.system.service.IArticleService; 1
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleAuthorDetailVO> getArticlesWithAuthorDetails(String titleKeyword) {
    return articleMapper.selectFromArticleAuthorView(titleKeyword);
}
}