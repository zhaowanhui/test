package com.zwh.util;

import org.apache.commons.lang3.StringUtils;

public class Result<T> {

    /**
     * 业务处理结果
     */
    private boolean result = false;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 接口提示信息
     */
    private String msg;

    /**
     * 接口返回值
     */
    private T value;

    public static ResultBuild withBuild() {
        return new ResultBuild<>();
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setData(T value) {
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static class ResultBuild<T> {

        private Result<T> result = new Result<>();

        public ResultBuild<T> result(boolean result) {
            this.result.setResult(result);
            return this;
        }

        public ResultBuild<T> code(Integer code) {
            this.result.setCode(code);
            return this;
        }

        public ResultBuild<T> msg(String msg) {
            this.result.setMsg(msg);
            return this;
        }

        public ResultBuild<T> data(T data) {
            this.result.setData(data);
            return this;
        }

        public Result<T> build() {
            return this.result;
        }
    }

    public static <T> Result<T> success() {
        return success(null, null);
    }

    public static <T> Result<T> success(T resultObj) {
        return success(resultObj, null);
    }

    public static <T> Result<T> success(T resultObj, String tipMsg) {
        ResultBuild<T> objectResultBuild = new ResultBuild<>();
        return objectResultBuild.code(200).result(true).data(resultObj).msg(tipMsg).build();
    }

    public static <T> Result<T> fail() {
        return fail(null, null);
    }

    public static <T> Result<T> fail(String errMsg) {
        return fail(null, errMsg);
    }

    public static <T> Result<T> fail(T resultObj, String errMsg) {
        ResultBuild<T> objectResultBuild = new ResultBuild<>();
        return objectResultBuild.result(false).data(resultObj).msg(StringUtils.defaultIfEmpty(errMsg, "操作失败!")).build();
    }

    public static <T> Result<T> result(boolean result, T resultObj, String errMsg) {
        return result ? Result.<T>success(resultObj) : Result.<T>fail(errMsg);
    }

    public static <T> Result<T> result(boolean result, T resultObj, String errMsg, Integer code) {
        ResultBuild<T> objectResultBuild = new ResultBuild<>();
        return objectResultBuild.result(result).data(resultObj).msg(result ? "操作成功." : StringUtils.defaultIfEmpty(errMsg, "操作失败!")).code(code)
                .build();
    }

}
