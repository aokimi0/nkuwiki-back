package cn.iocoder.yudao.module.knowledge.controller.admin.comments;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.knowledge.controller.admin.comments.vo.*;
import cn.iocoder.yudao.module.knowledge.dal.dataobject.comments.CommentsDO;
import cn.iocoder.yudao.module.knowledge.service.comments.CommentsService;

@Tag(name = "管理后台 - 评论信息")
@RestController
@RequestMapping("/knowledge/comments")
@Validated
public class CommentsController {

    @Resource
    private CommentsService commentsService;

    @PostMapping("/create")
    @Operation(summary = "创建评论信息")
    @PreAuthorize("@ss.hasPermission('knowledge:comments:create')")
    public CommonResult<Long> createComments(@Valid @RequestBody CommentsSaveReqVO createReqVO) {
        return success(commentsService.createComments(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评论信息")
    @PreAuthorize("@ss.hasPermission('knowledge:comments:update')")
    public CommonResult<Boolean> updateComments(@Valid @RequestBody CommentsSaveReqVO updateReqVO) {
        commentsService.updateComments(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评论信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('knowledge:comments:delete')")
    public CommonResult<Boolean> deleteComments(@RequestParam("id") Long id) {
        commentsService.deleteComments(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除评论信息")
                @PreAuthorize("@ss.hasPermission('knowledge:comments:delete')")
    public CommonResult<Boolean> deleteCommentsList(@RequestParam("ids") List<Long> ids) {
        commentsService.deleteCommentsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评论信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('knowledge:comments:query')")
    public CommonResult<CommentsRespVO> getComments(@RequestParam("id") Long id) {
        CommentsDO comments = commentsService.getComments(id);
        return success(BeanUtils.toBean(comments, CommentsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评论信息分页")
    @PreAuthorize("@ss.hasPermission('knowledge:comments:query')")
    public CommonResult<PageResult<CommentsRespVO>> getCommentsPage(@Valid CommentsPageReqVO pageReqVO) {
        PageResult<CommentsDO> pageResult = commentsService.getCommentsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CommentsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评论信息 Excel")
    @PreAuthorize("@ss.hasPermission('knowledge:comments:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCommentsExcel(@Valid CommentsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CommentsDO> list = commentsService.getCommentsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评论信息.xls", "数据", CommentsRespVO.class,
                        BeanUtils.toBean(list, CommentsRespVO.class));
    }

}