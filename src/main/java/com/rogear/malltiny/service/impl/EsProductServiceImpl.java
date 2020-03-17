package com.rogear.malltiny.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.rogear.malltiny.dao.EsProductDao;
import com.rogear.malltiny.nosql.elasticsearch.document.EsProduct;
import com.rogear.malltiny.nosql.elasticsearch.repository.EsProductRepository;
import com.rogear.malltiny.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 商品搜索管理实现类
 * Created by Rogear on 2020/3/17
 **/
@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    private EsProductDao esProductDao;

    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public int importAll() {
        List<EsProduct> allEsProductList = esProductDao.getAllEsProductList(null);
        Iterable<EsProduct> iterable = esProductRepository.saveAll(allEsProductList);
        Iterator<EsProduct> iterator = iterable.iterator();
        int result = 0;
        while (iterator.hasNext()){
            result ++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        esProductRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> allEsProductList = esProductDao.getAllEsProductList(id);
        if (allEsProductList.size() > 0){
            EsProduct esProduct = allEsProductList.get(0);
            result = (EsProduct) esProductRepository.save(esProduct);
        }
        return result;
    }

    @Override
    public void detele(List<Long> ids) {
        if (!CollectionUtil.isEmpty(ids)){
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids){
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            esProductRepository.deleteAll(esProductList);
        }

    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<EsProduct> esProductList = esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
        return esProductList;
    }
}
