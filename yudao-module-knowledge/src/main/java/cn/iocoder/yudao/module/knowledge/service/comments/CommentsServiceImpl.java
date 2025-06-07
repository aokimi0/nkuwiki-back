package cn.iocoder.yudao.module.knowledge.service.comments;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.knowledge.controller.admin.comments.vo.*;
import cn.iocoder.yudao.module.knowledge.dal.dataobject.comments.CommentsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.knowledge.dal.mysql.comments.CommentsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * 评论信息 Service 实现类
 *
 * @author aokimi
 */
@Service
@Validated
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentsMapper commentsMapper;

    @Override
    public Long createComments(CommentsSaveReqVO createReqVO) {
        // 插入
        CommentsDO comments = BeanUtils.toBean(createReqVO, CommentsDO.class);
        commentsMapper.insert(comments);
        // 返回
        return comments.getId();
    }

    @Override
    public void updateComments(CommentsSaveReqVO updateReqVO) {
        // 校验存在
        validateCommentsExists(updateReqVO.getId());
        // 更新
        CommentsDO updateObj = BeanUtils.toBean(updateReqVO, CommentsDO.class);
        commentsMapper.updateById(updateObj);
    }

    @Override
    public void deleteComments(Long id) {
        // 校验存在
        validateCommentsExists(id);
        // 删除
        commentsMapper.deleteById(id);
    }

    @Override
        public void deleteCommentsListByIds(List<Long> ids) {
        // 校验存在
        validateCommentsExists(ids);
        // 删除
        commentsMapper.deleteByIds(ids);
        }

    private void validateCommentsExists(List<Long> ids) {
        List<CommentsDO> list = commentsMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(COMMENTS_NOT_EXISTS);
        }
    }

    private void validateCommentsExists(Long id) {
        if (commentsMapper.selectById(id) == null) {
            throw exception(COMMENTS_NOT_EXISTS);
        }
    }

    @Override
    public CommentsDO getComments(Long id) {
        return commentsMapper.selectById(id);
    }

    @Override
    public PageResult<CommentsDO> getCommentsPage(CommentsPageReqVO pageReqVO) {
        return commentsMapper.selectPage(pageReqVO);
    }

}