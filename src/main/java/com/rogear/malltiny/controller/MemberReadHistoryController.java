package com.rogear.malltiny.controller;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.nosql.mongodb.document.MemberReadHistory;
import com.rogear.malltiny.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 用户商品浏览记录管理Controller
 * Created by Rogear on 2020/3/17
 **/
@Api(tags = "MemberReadHistoryController",description = "用户商品浏览记录管理")
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @PostMapping("/create")
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory){
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.faild();
        }
    }

    @ApiOperation("删除浏览记录")
    @PostMapping("/delete")
    public CommonResult delete(@PathParam("ids")List<String> ids){
        int i = memberReadHistoryService.delete(ids);
        if (i > 0){
            return CommonResult.success(i);
        } else {
            return CommonResult.faild();
        }
    }

    @ApiOperation("根据用户id查询浏览记录")
    @GetMapping("/list/{memberId}")
    public CommonResult<List<MemberReadHistory>> list(@PathVariable Long memberId){
        List<MemberReadHistory> list = memberReadHistoryService.list(memberId);
        return CommonResult.success(list);
    }
}
