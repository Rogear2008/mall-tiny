package com.rogear.malltiny.service.impl;

import com.rogear.malltiny.nosql.mongodb.document.MemberReadHistory;
import com.rogear.malltiny.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.rogear.malltiny.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户商品浏览记录实现类
 * Created by Rogear on 2020/3/17
 **/
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> memberReadHistoryList = new ArrayList<>();
        for (String id: ids){
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            memberReadHistoryList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(memberReadHistoryList);
        return memberReadHistoryList.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
