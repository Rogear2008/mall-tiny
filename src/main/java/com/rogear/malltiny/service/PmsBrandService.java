package com.rogear.malltiny.service;

import com.rogear.malltiny.mbg.model.PmsBrand;

import java.lang.management.PlatformManagedObject;
import java.util.List;

/**
 * Created by Rogear on 2020/3/13
 **/
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand pmsBrand);

    int updateBrand(Long id, PmsBrand pmsBrand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum,int pageSize);

    PmsBrand getBrand(Long id);
}
