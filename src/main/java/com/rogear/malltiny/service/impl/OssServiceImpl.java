package com.rogear.malltiny.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.rogear.malltiny.dto.OssCallbackParam;
import com.rogear.malltiny.dto.OssCallbackResult;
import com.rogear.malltiny.dto.OssPolicyResult;
import com.rogear.malltiny.service.OssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * OSS上传管理实现类
 * Created by Rogear on 2020/3/18
 **/
@Service
public class OssServiceImpl implements OssService {

    private static Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);

    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_POLICY_EXPIRE;

    @Value("${aliyun.oss.policy.maxSize}")
    private int ALIYUN_OSS_POLICY_MAXSIZE;

    @Value("${aliyun.oss.policy.callback}")
    private String ALIYUN_OSS_POLICY_CALLBACK;

    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKETNAME;

    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;

    @Value("${aliyun.oss.policy.dir.prefix}")
    private String ALIYUN_OSS_POLICY_DIR_PREFIX;

    @Autowired
    private OSSClient ossClient;

    @Override
    public OssPolicyResult policy() {
        OssPolicyResult result = new OssPolicyResult();
        //存储目录
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dir = ALIYUN_OSS_POLICY_DIR_PREFIX + simpleDateFormat.format(new Date());
        //签名有效期
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_POLICY_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        //文件大小
        long maxSize = ALIYUN_OSS_POLICY_MAXSIZE * 1024 * 1024;
        //回调
        OssCallbackParam ossCallbackParam = new OssCallbackParam();
        ossCallbackParam.setCallbackUrl(ALIYUN_OSS_POLICY_CALLBACK);
        ossCallbackParam.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        ossCallbackParam.setCallbackBodyType("application/x-www-form-urlencoded");
        //提交节点
        String action = "http://" + ALIYUN_OSS_BUCKETNAME + "." + ALIYUN_OSS_ENDPOINT;

        try {
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
            byte[] bytes = postPolicy.getBytes("utf-8");
            String policy = BinaryUtil.toBase64String(bytes);
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(ossCallbackParam).toString().getBytes("utf-8"));
            //返回结果
            result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callbackData);
            result.setHost(action);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("签名生成失败",e);
        }
        return result;
    }

    @Override
    public OssCallbackResult callback(HttpServletRequest request) {
        OssCallbackResult result = new OssCallbackResult();
        String filename = request.getParameter("filename");
        filename = "http://".concat(ALIYUN_OSS_BUCKETNAME).concat(".").concat(ALIYUN_OSS_ENDPOINT).concat("/").concat(filename);
        result.setFilename(filename);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }
}
