package com.rogear.malltiny.service.impl;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.component.CancelOrderSender;
import com.rogear.malltiny.dto.OrderParam;
import com.rogear.malltiny.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理实现类
 * Created by Rogear on 2020/3/18
 **/
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        //TODO:执行一系列下单的操作，具体内容见完整项目
        LOGGER.info("process generateOrder");
        //下单完成后开启一个延时任务，用户没有在规定时间内付款则取消订单（生成订单的时候会生成orderId）
        sendDelayCancelOrder(1L);
        return CommonResult.success(null,"下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        //TODO:执行一系列取消订单的操作，具体内容见完整项目
        LOGGER.info("process cancelOrder orderI:{}",orderId);

    }

    private void sendDelayCancelOrder(long orderId) {
        //设置订单超时时间
        long delayTime = 60*1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId,delayTime);
    }
}
