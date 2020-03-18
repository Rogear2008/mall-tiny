package com.rogear.malltiny.service;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.dto.OrderParam;

/**
 * 前台订单管理Service
 * Created by Rogear on 2020/3/18
 **/
public interface OmsPortalOrderService {

    /**
     * 生成订单
     * @param orderParam
     * @return
     */
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消超时订单
     * @param orderId
     */
    void cancelOrder(Long orderId);
}
