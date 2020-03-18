package com.rogear.malltiny.dto;

import lombok.Getter;

/**
 * 消息队列枚举配置
 * Created by Rogear on 2020/3/17
 **/
@Getter
public enum QueueEnum {

    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct","mall.order.cancel","mall.order.cancel"),

    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl","mall.order.direct.ttl","mall.order.direct.ttl");

    /**
     * 交换机名称
     */
    private String exchange;

    /**
     * 队列名称
     */
    private String name;

    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum() {
    }

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
