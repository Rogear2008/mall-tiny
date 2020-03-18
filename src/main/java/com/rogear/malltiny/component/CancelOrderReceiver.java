package com.rogear.malltiny.component;

import com.rogear.malltiny.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 * Created by Rogear on 2020/3/18
 **/
@Component
public class CancelOrderReceiver {

    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    public void handle(Long orderId){
        LOGGER.info("receive delay message orderId:{}",orderId);
        omsPortalOrderService.cancelOrder(orderId);
    }
}
