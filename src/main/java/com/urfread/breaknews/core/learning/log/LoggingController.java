package com.urfread.breaknews.core.learning.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class LoggingController {

    // 使用 SLF4J Logger 来进行日志记录
    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    // 定义一个 GET 请求的端点,用于测试日志是否 正常、可用
    @GetMapping("/logExample")
    public String logExample() {
        // 输出信息日志
        logger.info("logExample endpoint has been called");

        // 输出调试日志：可以帮助开发者在调试阶段追踪程序执行
        logger.debug("Debugging log for logExample endpoint");

        // 输出警告日志：记录可能对系统稳定性造成影响的情况
        logger.warn("This is a warning message");

        // 输出错误日志：当系统发生异常或错误时，记录详细信息
        logger.error("This is an error message");

        // 返回响应信息
        return "Logging example complete!";
    }
    @GetMapping("/logExecutionTime1")
    @LogExecutionTime // 使用 AOP 注解记录执行时间
    public String logExecutionTime() {
        // 模拟业务逻辑
        try {
            Thread.sleep(300); // 模拟处理时间
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
        }
        return "Logging example complete!";
    }

    @GetMapping("/logExecutionTime2")
    @LogExecutionTime // 使用 AOP 注解记录执行时间
    public String logExecutionTime2() {
        // 模拟业务逻辑
//        try {
//            Thread.sleep(500); // 模拟处理时间
//        } catch (InterruptedException e) {
//            logger.error("Thread interrupted", e);
//        }
        return "Another endpoint complete!";
    }
}
