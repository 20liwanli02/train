package com.jiawa.train.common.exception;

/**
 * 异常类，继承RuntimeException，避免抛异常
 */
public class BusinessException extends RuntimeException {

    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    public BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 不写入堆栈信息，提高性能
     * 消除多余的日志。因为产生异常的原因自己已经知晓，没必要太详细
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
