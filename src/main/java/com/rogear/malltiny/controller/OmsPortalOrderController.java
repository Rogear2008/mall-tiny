package com.rogear.malltiny.controller;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.dto.OrderParam;
import com.rogear.malltiny.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台订单管理controller
 * Created by Rogear on 2020/3/18
 **/
@Api(tags = "OmsPortalOrderController",description = "前台订单管理")
@RestController
@RequestMapping("/order")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("生成订单")
    @PostMapping("/generateOrder")
    public CommonResult generateOrder(@RequestBody OrderParam orderParam){
        CommonResult commonResult = omsPortalOrderService.generateOrder(orderParam);
        return commonResult;
    }
}
