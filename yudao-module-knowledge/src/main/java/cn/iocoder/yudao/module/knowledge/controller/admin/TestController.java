package cn.iocoder.yudao.module.knowledge.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 知识")
@RestController
@RequestMapping("/knowledge") // 接口的基础路径
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "测试接口 - Hello")
    public CommonResult<String> get() {
        return success("true");
    }
}