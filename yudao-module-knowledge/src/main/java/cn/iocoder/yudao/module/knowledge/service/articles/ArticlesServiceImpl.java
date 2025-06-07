package cn.iocoder.yudao.module.knowledge.service.articles;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.knowledge.controller.admin.articles.vo.*;
import cn.iocoder.yudao.module.knowledge.dal.dataobject.articles.ArticlesDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.knowledge.dal.mysql.articles.ArticlesMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * 文章信息 Service 实现类
 *
 * @author aokimi
 */
@Service
@Validated
public class ArticlesServiceImpl implements ArticlesService {

    @Resource
    private ArticlesMapper articlesMapper;

    @Override
    public Long createArticles(ArticlesSaveReqVO createReqVO) {
        // 插入
        ArticlesDO articles = BeanUtils.toBean(createReqVO, ArticlesDO.class);
        articlesMapper.insert(articles);
        // 返回
        return articles.getId();
    }

    @Override
    public void updateArticles(ArticlesSaveReqVO updateReqVO) {
        // 校验存在
        validateArticlesExists(updateReqVO.getId());
        // 更新
        ArticlesDO updateObj = BeanUtils.toBean(updateReqVO, ArticlesDO.class);
        articlesMapper.updateById(updateObj);
    }

    @Override
    public void deleteArticles(Long id) {
        // 校验存在
        validateArticlesExists(id);
        // 删除
        articlesMapper.deleteById(id);
    }

    @Override
        public void deleteArticlesListByIds(List<Long> ids) {
        // 校验存在
        validateArticlesExists(ids);
        // 删除
        articlesMapper.deleteByIds(ids);
        }

    private void validateArticlesExists(List<Long> ids) {
        List<ArticlesDO> list = articlesMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(ARTICLES_NOT_EXISTS);
        }
    }

    private void validateArticlesExists(Long id) {
        if (articlesMapper.selectById(id) == null) {
            throw exception(ARTICLES_NOT_EXISTS);
        }
    }

    @Override
    public ArticlesDO getArticles(Long id) {
        return articlesMapper.selectById(id);
    }

    @Override
    public PageResult<ArticlesDO> getArticlesPage(ArticlesPageReqVO pageReqVO) {
        return articlesMapper.selectPage(pageReqVO);
    }

}