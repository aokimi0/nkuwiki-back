package cn.iocoder.yudao.module.knowledge.controller.admin.articles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 文章信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ArticlesRespVO {

    @Schema(description = "文章编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "14653")
    @ExcelProperty("文章编号")
    private Long id;

    @Schema(description = "文章标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("文章标题")
    private String title;

    @Schema(description = "文章内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("文章内容")
    private String content;

    @Schema(description = "发布时间")
    @ExcelProperty("发布时间")
    private LocalDateTime publishDate;

    @Schema(description = "作者ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10530")
    @ExcelProperty("作者ID")
    private Long authorId;

    @Schema(description = "来源平台")
    @ExcelProperty("来源平台")
    private String sourcePlatform;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}