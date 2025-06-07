package cn.iocoder.yudao.module.knowledge.controller.admin.comments.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 评论信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CommentsRespVO {

    @Schema(description = "评论编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "26337")
    @ExcelProperty("评论编号")
    private Long id;

    @Schema(description = "评论内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评论内容")
    private String commentContent;

    @Schema(description = "评论时间")
    @ExcelProperty("评论时间")
    private LocalDateTime commentDate;

    @Schema(description = "评论用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29769")
    @ExcelProperty("评论用户ID")
    private Long userId;

    @Schema(description = "文章ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26590")
    @ExcelProperty("文章ID")
    private Long articleId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}