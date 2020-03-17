package com.rogear.malltiny.dao;

import com.rogear.malltiny.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索商品管理自定义dao
 * Created by Rogear on 2020/3/17
 **/
public interface EsProductDao {

    List<EsProduct> getAllEsProductList(@Param("id")Long id);
}
