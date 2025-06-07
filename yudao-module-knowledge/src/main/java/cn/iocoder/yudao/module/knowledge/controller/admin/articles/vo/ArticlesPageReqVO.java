package cn.iocoder.yudao.module.knowledge.controller.admin.articles.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 文章信息分页 Request VO")
@Data
public class ArticlesPageReqVO extends PageParam {

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章内容")
    private String content;

    @Schema(description = "发布时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] publishDate;

    @Schema(description = "作者ID", example = "10530")
    private Long authorId;

    @Schema(description = "来源平台")
    private String sourcePlatform;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}