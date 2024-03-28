package com.jiawa.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.jiawa.train.member.domain.Member;
import com.jiawa.train.member.domain.MemberExample;
import com.jiawa.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    /**
     *查询用户记录数量
     */
    @Resource
    private MemberMapper memberMapper;

    public int count(){
        int count = Math.toIntExact(memberMapper.countByExample(null));
        return count;
    }

    /**
     * 注册会员账号：插入用户记录
     */
    public long register(String mobile){
        //查询数据库中是否已经存在mobile
        //类似与where
        MemberExample memberExample = new MemberExample();
        //查询是否有mobile相同的例子
        memberExample.createCriteria().andMobileEqualTo(mobile);
        //根据例子查询出是否为空或者数组
        List<Member> list = memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(list)){
            //返回的是一个数组，一条用户记录
//            return list.get(0).getId();
            throw new RuntimeException("手机号已注册！");
        }

        //封装插入用户记录
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        //Member record：传入一个member的对象
        memberMapper.insert(member);
        return member.getId();
    }
}
