package com.rogear.malltiny.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 获取OSS上传文件授权返回对象
 * Created by Rogear on 2020/3/18
 **/
public class OssPolicyResult implements Serializable {

    private static final long serialVersionUID = 5246258695772061550L;

    @ApiModelProperty("访问身份验证的用户标识")
    private String accessKeyId;

    @ApiModelProperty("用户表单上传的策略，经过base64编码过的字符串")
    private String policy;

    @ApiModelProperty("对policy签名后的字符串")
    private String signature;

    @ApiModelProperty("上传文件夹子路径前缀")
    private String dir;

    @ApiModelProperty("oss对外访问域名")
    private String host;

    @ApiModelProperty("上传成功后的回调设置")
    private String callback;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
