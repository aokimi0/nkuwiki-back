package cn.iocoder.yudao.module.knowledge.dal.dataobject.articles;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 文章信息 DO
 *
 * @author aokimi
 */
@TableName("knowledge_articles")
@KeySequence("knowledge_articles_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesDO extends BaseDO {

    /**
     * 文章编号
     */
    @TableId
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 发布时间
     */
    private LocalDateTime publishDate;
    /**
     * 作者ID
     */
    private Long authorId;
    /**
     * 来源平台
     */
    private String sourcePlatform;


}