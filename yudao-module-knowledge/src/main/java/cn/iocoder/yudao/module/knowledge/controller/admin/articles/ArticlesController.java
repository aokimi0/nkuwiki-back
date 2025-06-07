package cn.iocoder.yudao.module.knowledge.controller.admin.articles;

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

import cn.iocoder.yudao.module.knowledge.controller.admin.articles.vo.*;
import cn.iocoder.yudao.module.knowledge.dal.dataobject.articles.ArticlesDO;
import cn.iocoder.yudao.module.knowledge.service.articles.ArticlesService;

@Tag(name = "管理后台 - 文章信息")
@RestController
@RequestMapping("/knowledge/articles")
@Validated
public class ArticlesController {

    @Resource
    private ArticlesService articlesService;

    @PostMapping("/create")
    @Operation(summary = "创建文章信息")
    @PreAuthorize("@ss.hasPermission('knowledge:articles:create')")
    public CommonResult<Long> createArticles(@Valid @RequestBody ArticlesSaveReqVO createReqVO) {
        return success(articlesService.createArticles(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新文章信息")
    @PreAuthorize("@ss.hasPermission('knowledge:articles:update')")
    public CommonResult<Boolean> updateArticles(@Valid @RequestBody ArticlesSaveReqVO updateReqVO) {
        articlesService.updateArticles(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文章信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('knowledge:articles:delete')")
    public CommonResult<Boolean> deleteArticles(@RequestParam("id") Long id) {
        articlesService.deleteArticles(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除文章信息")
                @PreAuthorize("@ss.hasPermission('knowledge:articles:delete')")
    public CommonResult<Boolean> deleteArticlesList(@RequestParam("ids") List<Long> ids) {
        articlesService.deleteArticlesListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得文章信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('knowledge:articles:query')")
    public CommonResult<ArticlesRespVO> getArticles(@RequestParam("id") Long id) {
        ArticlesDO articles = articlesService.getArticles(id);
        return success(BeanUtils.toBean(articles, ArticlesRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得文章信息分页")
    @PreAuthorize("@ss.hasPermission('knowledge:articles:query')")
    public CommonResult<PageResult<ArticlesRespVO>> getArticlesPage(@Valid ArticlesPageReqVO pageReqVO) {
        PageResult<ArticlesDO> pageResult = articlesService.getArticlesPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ArticlesRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出文章信息 Excel")
    @PreAuthorize("@ss.hasPermission('knowledge:articles:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportArticlesExcel(@Valid ArticlesPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ArticlesDO> list = articlesService.getArticlesPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "文章信息.xls", "数据", ArticlesRespVO.class,
                        BeanUtils.toBean(list, ArticlesRespVO.class));
    }

}