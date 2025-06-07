package cn.iocoder.yudao.module.knowledge.controller.admin.articles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 文章信息新增/修改 Request VO")
@Data
public class ArticlesSaveReqVO {

    @Schema(description = "文章编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "14653")
    private Long id;

    @Schema(description = "文章标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "文章标题不能为空")
    private String title;

    @Schema(description = "文章内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "文章内容不能为空")
    private String content;

    @Schema(description = "发布时间")
    private LocalDateTime publishDate;

    @Schema(description = "作者ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10530")
    @NotNull(message = "作者ID不能为空")
    private Long authorId;

    @Schema(description = "来源平台")
    private String sourcePlatform;

}