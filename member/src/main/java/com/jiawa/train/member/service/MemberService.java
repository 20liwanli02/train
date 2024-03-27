package com.jiawa.train.member.service;

import com.jiawa.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count(){
        int count = Math.toIntExact(memberMapper.countByExample(null));
        return count;
    }
}
