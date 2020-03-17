package com.rogear.malltiny.controller;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.common.api.ResultCode;
import com.rogear.malltiny.mbg.model.PmsBrand;
import com.rogear.malltiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理Controller
 * Created by Rogear on 2020/3/13
 **/
@Api(tags = "PmsBrandController",description = "品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PmsBrandService pmsBrandService;

    @ApiOperation("获取所有品牌")
    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> listAllBrand(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    };

    @ApiOperation("新增品牌")
    @PostMapping("/createBrand")
    @PreAuthorize("hasAuthority('pms:brand:create')")
    public CommonResult createBrand(@PathVariable  PmsBrand pmsBrand){
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count > 0){
            LOGGER.info("createBrand success:{}",pmsBrand);
            return CommonResult.success(pmsBrand);
        } else {
            LOGGER.info("createBrand failed:{}",pmsBrand);
            return CommonResult.faild(ResultCode.FAILED);
        }
    };

    @ApiOperation("根据id更新品牌")
    @PostMapping("/updateBrand/{id}")
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommonResult updateBrand(@PathVariable Long id, @RequestBody PmsBrand pmsBrandDto){
        int count = pmsBrandService.updateBrand(id,pmsBrandDto);
        if (count > 0){
            LOGGER.info("updateBrand success:{}",pmsBrandDto);
            return CommonResult.success(pmsBrandDto);
        } else {
            LOGGER.info("updateBrand failed:{}",pmsBrandDto);
            return CommonResult.faild(ResultCode.FAILED);
        }
    };

    @ApiOperation("根据id删除品牌")
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    public CommonResult deleteBrand(@PathVariable Long id){
        int count = pmsBrandService.deleteBrand(id);
        if (count > 0){
            LOGGER.info("deleteBrand success:{}",id);
            return CommonResult.success(id);
        } else {
            LOGGER.info("deleteBrand failed:{}",id);
            return CommonResult.faild(ResultCode.FAILED);
        }
    };

    @ApiOperation("分页查询品牌列表")
    @GetMapping("/listBrand")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> listBrand(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                  @RequestParam(value = "pageSize",defaultValue = "3") int pageSize){
        List<PmsBrand> pmsBrandList = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(pmsBrandList);
    };

    @ApiOperation("根据id查找品牌")
    @GetMapping("/getBrand/{id}")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<PmsBrand> getBrand(@PathVariable Long id){
        return CommonResult.success(pmsBrandService.getBrand(id));
    };
}
