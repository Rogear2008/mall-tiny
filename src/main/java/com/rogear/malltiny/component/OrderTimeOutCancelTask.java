package com.rogear.malltiny.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单超时取消并且解锁库存
 * Created by Rogear on 2020/3/17
 **/
@Component
public class OrderTimeOutCancelTask {

    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，超时没有付费的订单则作废，并且释放锁定的库存
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder(){
        //TODO:调用超时方法
        LOGGER.info("取消订单，并且跟库sku编号释放锁定的库存");
    }
}
