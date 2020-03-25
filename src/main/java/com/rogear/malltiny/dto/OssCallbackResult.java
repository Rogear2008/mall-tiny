package com.rogear.malltiny.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * OSS上传文件的回调结果
 * Created by Rogear on 2020/3/18
 **/
public class OssCallbackResult implements Serializable {

    private static final long serialVersionUID = 9173866668700250873L;

    @ApiModelProperty("文件名称")
    private String filename;

    @ApiModelProperty("文件大小")
    private String size;

    @ApiModelProperty("文件的mimeType")
    private String mimeType;

    @ApiModelProperty("图片的宽")
    private String width;

    @ApiModelProperty("图片的高")
    private String height;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
