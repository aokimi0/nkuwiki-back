package cn.iocoder.yudao.module.knowledge.controller.admin.comments.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评论信息新增/修改 Request VO")
@Data
public class CommentsSaveReqVO {

    @Schema(description = "评论编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "26337")
    private Long id;

    @Schema(description = "评论内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评论内容不能为空")
    private String commentContent;

    @Schema(description = "评论时间")
    private LocalDateTime commentDate;

    @Schema(description = "评论用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29769")
    @NotNull(message = "评论用户ID不能为空")
    private Long userId;

    @Schema(description = "文章ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26590")
    @NotNull(message = "文章ID不能为空")
    private Long articleId;

}