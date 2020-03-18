package com.rogear.malltiny.dto;

import java.io.Serializable;

/**
 * 生成订单时的传入参数
 * Created by Rogear on 2020/3/18
 **/
public class OrderParam implements Serializable {

    private static final long serialVersionUID = 4465157242953824387L;
    /**
     * 收货地址id
     */
    private Long memberReceivedAddressId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 使用的积分数
     */
    private Integer useIntegration;

    /**
     * 支付方式
     */
    private Integer payType;

    public Long getMemberReceivedAddressId() {
        return memberReceivedAddressId;
    }

    public void setMemberReceivedAddressId(Long memberReceivedAddressId) {
        this.memberReceivedAddressId = memberReceivedAddressId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Integer getUseIntegration() {
        return useIntegration;
    }

    public void setUseIntegration(Integer useIntegration) {
        this.useIntegration = useIntegration;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
