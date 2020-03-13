package com.rogear.malltiny.common.api;

/**
 * 通用返回对象
 * Created by Rogear on 2020/3/13
 **/
public class CommonResult<T> {

    private long code;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(ResultCode resultCode,T data){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> success(T data){
        return new CommonResult<>(ResultCode.SUCCESS,data);
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @param message
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> success(T data,String message){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 返回失败结果
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> faild(IErrorCode errorCode){
        return new CommonResult<>(errorCode.getCode(),errorCode.getMessage(),null);
    }

    /**
     * 返回失败结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> faild(String message){
        return new CommonResult<>(ResultCode.FAILED.getCode(),message,null);
    }

    /**
     * 返回失败结果
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> faild(){
        return faild(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> validateFaild(){
        return faild(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> validateFaild(String message){
        return faild(message);
    }

    /**
     * 未登录返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> unauthorized(T data){
        return new CommonResult<>(ResultCode.UNAUTHORIZED,data);
    }

    /**
     * 未授权返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T>CommonResult<T> forbidden(T data){
        return new CommonResult<>(ResultCode.FORBIDEN,data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
