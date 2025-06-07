package cn.iocoder.yudao.module.knowledge.service.articles;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.knowledge.controller.admin.articles.vo.*;
import cn.iocoder.yudao.module.knowledge.dal.dataobject.articles.ArticlesDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 文章信息 Service 接口
 *
 * @author aokimi
 */
public interface ArticlesService {

    /**
     * 创建文章信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArticles(@Valid ArticlesSaveReqVO createReqVO);

    /**
     * 更新文章信息
     *
     * @param updateReqVO 更新信息
     */
    void updateArticles(@Valid ArticlesSaveReqVO updateReqVO);

    /**
     * 删除文章信息
     *
     * @param id 编号
     */
    void deleteArticles(Long id);

    /**
    * 批量删除文章信息
    *
    * @param ids 编号
    */
    void deleteArticlesListByIds(List<Long> ids);

    /**
     * 获得文章信息
     *
     * @param id 编号
     * @return 文章信息
     */
    ArticlesDO getArticles(Long id);

    /**
     * 获得文章信息分页
     *
     * @param pageReqVO 分页查询
     * @return 文章信息分页
     */
    PageResult<ArticlesDO> getArticlesPage(ArticlesPageReqVO pageReqVO);

}