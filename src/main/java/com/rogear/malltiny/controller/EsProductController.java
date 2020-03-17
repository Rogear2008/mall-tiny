package com.rogear.malltiny.controller;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.nosql.elasticsearch.document.EsProduct;
import com.rogear.malltiny.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 商品搜索管理Controller
 * Created by Rogear on 2020/3/17
 **/
@Api(tags = "EsProductController",description = "商品搜索管理")
@RestController
@RequestMapping("/esProduct")
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "将所有商品信息导入到ES")
    @PostMapping("/importAll")
    public CommonResult<Integer> importAll(){
        int result = esProductService.importAll();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "根据id删除商品")
    @PostMapping("/delete/{id}")
    public CommonResult<Object> delete(@PathVariable Long id){
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @PostMapping("/delete/batch")
    public CommonResult<Object> delete(@PathParam("ids")List<Long> ids){
        esProductService.detele(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @PostMapping("/create/{id}")
    public CommonResult<EsProduct> create(@PathVariable Long id){
        EsProduct esProduct = esProductService.create(id);
        if (null == esProduct){
            return CommonResult.faild();
        } else {
            return CommonResult.success(esProduct);
        }
    }

    @ApiOperation(value = "简单搜索")
    @GetMapping("/search")
    public CommonResult<Page<EsProduct>> search(@RequestParam(required = false)String keyword,
                                                @RequestParam(required = false,defaultValue = "0")Integer pageNum,
                                                @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        Page<EsProduct> search = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(search);
    }
}
