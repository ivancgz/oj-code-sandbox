package com.ivan.ivancodesandbox.controller;

import com.ivan.ivancodesandbox.JavaNativeCodeSandbox;
import com.ivan.ivancodesandbox.model.ExecuteCodeRequest;
import com.ivan.ivancodesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/")
public class MainController {

    // 定义鉴权请求头和秘钥
    private static final String AUTH_REQUEST_HEADER = "auth";
    private static final String AUTH_REQUEST_SECRET = "secretKey";
    @Resource
    private JavaNativeCodeSandbox javaNativeCodeSandbox;

    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }

    /**
     * 执行代码
     * @param executeCodeRequest 执行代码请求
     * @return
     */
    @PostMapping("/executeCode")
    public ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        // 基本认证
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!AUTH_REQUEST_SECRET.equals(authHeader)) {
            response.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数为空");
        }
        return javaNativeCodeSandbox.executeCode(executeCodeRequest);
    }
}
