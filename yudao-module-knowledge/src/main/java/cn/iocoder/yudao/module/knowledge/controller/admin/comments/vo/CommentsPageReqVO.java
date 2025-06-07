package cn.iocoder.yudao.module.knowledge.controller.admin.comments.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评论信息分页 Request VO")
@Data
public class CommentsPageReqVO extends PageParam {

    @Schema(description = "评论内容")
    private String commentContent;

    @Schema(description = "评论时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] commentDate;

    @Schema(description = "评论用户ID", example = "29769")
    private Long userId;

    @Schema(description = "文章ID", example = "26590")
    private Long articleId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}