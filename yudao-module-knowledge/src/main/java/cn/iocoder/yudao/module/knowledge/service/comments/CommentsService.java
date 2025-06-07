package cn.iocoder.yudao.module.knowledge.service.comments;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.knowledge.controller.admin.comments.vo.*;
import cn.iocoder.yudao.module.knowledge.dal.dataobject.comments.CommentsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 评论信息 Service 接口
 *
 * @author aokimi
 */
public interface CommentsService {

    /**
     * 创建评论信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createComments(@Valid CommentsSaveReqVO createReqVO);

    /**
     * 更新评论信息
     *
     * @param updateReqVO 更新信息
     */
    void updateComments(@Valid CommentsSaveReqVO updateReqVO);

    /**
     * 删除评论信息
     *
     * @param id 编号
     */
    void deleteComments(Long id);

    /**
    * 批量删除评论信息
    *
    * @param ids 编号
    */
    void deleteCommentsListByIds(List<Long> ids);

    /**
     * 获得评论信息
     *
     * @param id 编号
     * @return 评论信息
     */
    CommentsDO getComments(Long id);

    /**
     * 获得评论信息分页
     *
     * @param pageReqVO 分页查询
     * @return 评论信息分页
     */
    PageResult<CommentsDO> getCommentsPage(CommentsPageReqVO pageReqVO);

}