package com.rogear.malltiny.service;

import com.rogear.malltiny.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * 用户商品浏览记录Service
 * Created by Rogear on 2020/3/17
 **/
public interface MemberReadHistoryService {

    /**
     * 生成用户商品浏览记录
     * @param memberReadHistory
     * @return
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     * @param ids
     * @return
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览记录
     * @param memberId
     * @return
     */
    List<MemberReadHistory> list(Long memberId);
}
