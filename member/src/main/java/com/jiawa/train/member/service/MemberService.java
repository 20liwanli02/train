package com.jiawa.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.jiawa.train.common.exception.BusinessException;
import com.jiawa.train.common.exception.BusinessExceptionEnum;
import com.jiawa.train.common.util.SnowUtil;
import com.jiawa.train.member.domain.Member;
import com.jiawa.train.member.domain.MemberExample;
import com.jiawa.train.member.mapper.MemberMapper;
import com.jiawa.train.member.req.MemberRegisterReq;
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
    public long register(MemberRegisterReq req){
        //将封装类中的属性拿出来，转为String类型
        String mobile = req.getMobile();

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
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        //封装插入用户记录
        Member member = new Member();
        //雪花算法
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        //Member record：传入一个member的对象
        memberMapper.insert(member);
        return member.getId();
    }
}
