package com.rogear.malltiny.service;

import com.rogear.malltiny.dto.OssCallbackResult;
import com.rogear.malltiny.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * OSS上传管理Service
 * Created by Rogear on 2020/3/18
 **/
public interface OssService {

    /**
     * oss上传策略生成
     * @return
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);
}
