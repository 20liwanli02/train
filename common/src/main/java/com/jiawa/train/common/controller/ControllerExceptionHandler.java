package com.jiawa.train.common.controller;

import com.jiawa.train.common.exception.BusinessException;
import com.jiawa.train.common.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 * 专门给controller用，用来拦截接口的
 */
@ControllerAdvice//实现拦截器效果
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有异常统一处理
     */
    @ExceptionHandler(value = Exception.class)//指定那种类型的异常调用方法
    @ResponseBody
    public CommonResp exceptionHandler(Exception e) throws Exception {
        // LOG.info("seata全局事务ID: {}", RootContext.getXID());
        // // 如果是在一次全局事务里出异常了，就不要包装返回值，将异常抛给调用方，让调用方回滚事务
        // if (StrUtil.isNotBlank(RootContext.getXID())) {
        //     throw e;
        // }
        CommonResp commonResp = new CommonResp();
        LOG.error("系统异常：", e);
        commonResp.setSuccess(false);
        commonResp.setMessage("系统出现异常，请联系管理员");
        return commonResp;
    }

    /**
     * 业务异常统一处理
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BusinessException e) {
        CommonResp commonResp = new CommonResp();
        LOG.error("业务异常：{}", e.getE().getDesc());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());
        return commonResp;
    }

    /**
     * 校验异常统一处理
     * 判断新出现的异常类型，根据新出现的异常类型，写对应的拦截方法
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BindException e) {
        CommonResp commonResp = new CommonResp();
        //拿异常的具体信息有讲究，可以断点调试查看
        LOG.error("校验异常：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }

    /**
     * 校验异常统一处理
     */
//    @ExceptionHandler(value = RuntimeException.class)
//    @ResponseBody
//    public CommonResp exceptionHandler(RuntimeException e) {
//        throw e;
//    }

}
