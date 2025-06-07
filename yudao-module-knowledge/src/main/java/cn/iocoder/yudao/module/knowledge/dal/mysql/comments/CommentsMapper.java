package cn.iocoder.yudao.module.knowledge.dal.mysql.comments;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.knowledge.dal.dataobject.comments.CommentsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.knowledge.controller.admin.comments.vo.*;

/**
 * 评论信息 Mapper
 *
 * @author aokimi
 */
@Mapper
public interface CommentsMapper extends BaseMapperX<CommentsDO> {

    default PageResult<CommentsDO> selectPage(CommentsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommentsDO>()
                .eqIfPresent(CommentsDO::getCommentContent, reqVO.getCommentContent())
                .betweenIfPresent(CommentsDO::getCommentDate, reqVO.getCommentDate())
                .eqIfPresent(CommentsDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CommentsDO::getArticleId, reqVO.getArticleId())
                .betweenIfPresent(CommentsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommentsDO::getId));
    }

}