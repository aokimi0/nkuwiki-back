package cn.iocoder.yudao.module.knowledge.dal.mysql.articles;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.knowledge.dal.dataobject.articles.ArticlesDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.knowledge.controller.admin.articles.vo.*;

/**
 * 文章信息 Mapper
 *
 * @author aokimi
 */
@Mapper
public interface ArticlesMapper extends BaseMapperX<ArticlesDO> {

    default PageResult<ArticlesDO> selectPage(ArticlesPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ArticlesDO>()
                .likeIfPresent(ArticlesDO::getTitle, reqVO.getTitle())      // title 模糊匹配
                .likeIfPresent(ArticlesDO::getContent, reqVO.getContent())  // <-- content 也修改为模糊匹配
                .betweenIfPresent(ArticlesDO::getPublishDate, reqVO.getPublishDate())
                .eqIfPresent(ArticlesDO::getAuthorId, reqVO.getAuthorId())
                .eqIfPresent(ArticlesDO::getSourcePlatform, reqVO.getSourcePlatform())
                .betweenIfPresent(ArticlesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ArticlesDO::getId));
    }

}