package cn.iocoder.yudao.module.knowledge.dal.dataobject.comments;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评论信息 DO
 *
 * @author aokimi
 */
@TableName("knowledge_comments")
@KeySequence("knowledge_comments_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDO extends BaseDO {

    /**
     * 评论编号
     */
    @TableId
    private Long id;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论时间
     */
    private LocalDateTime commentDate;
    /**
     * 评论用户ID
     */
    private Long userId;
    /**
     * 文章ID
     */
    private Long articleId;


}