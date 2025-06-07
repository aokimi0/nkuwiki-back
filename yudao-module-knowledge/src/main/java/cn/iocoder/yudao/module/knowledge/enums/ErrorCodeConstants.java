package cn.iocoder.yudao.module.knowledge.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Knowledge 模块的错误码
 */
public interface ErrorCodeConstants {

    // ========== 文章 1-001-001-000 ==========
    ErrorCode ARTICLES_NOT_EXISTS = new ErrorCode(1_001_001_000, "知识文章不存在");

    // ... 后续可以继续在这里添加其他业务的错误码
    ErrorCode COMMENTS_NOT_EXISTS = new ErrorCode(1_001_001_001, "评论信息不存在");

}