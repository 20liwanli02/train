package com.jiawa.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.jiawa.train.common.exception.BusinessException;
import com.jiawa.train.common.exception.BusinessExceptionEnum;
import com.jiawa.train.common.util.JwtUtil;
import com.jiawa.train.common.util.SnowUtil;
import com.jiawa.train.member.constants.RedisConstants;
import com.jiawa.train.member.domain.Member;
import com.jiawa.train.member.domain.MemberExample;
import com.jiawa.train.member.mapper.MemberMapper;
import com.jiawa.train.member.req.MemberLoginReq;
import com.jiawa.train.member.req.MemberRegisterReq;
import com.jiawa.train.member.req.MemberSendCodeReq;
import com.jiawa.train.member.resp.LoginCodeResp;
import com.jiawa.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

//    private String CODE;

    /**
     *查询用户记录数量
     */
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private RedisService redisService;

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
        String codeReq = req.getCode();
        String keyRedis = req.getKeyRedis();
        String result = redisService.getCacheObject(RedisConstants.LOGIN_CODE + keyRedis);

        //查询数据库中是否已经存在mobile
        Member memberDB = selectByMobile(mobile);

        if(ObjectUtil.isNotNull(memberDB)){
            //返回的是一个数组，一条用户记录
//            return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        if(result == null){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_NOT_EXIST);
        }

        //校验短信验证码
        if(!result.equals(codeReq)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
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

    /**
     *生成短信验证码
     */
    public LoginCodeResp sendCode(MemberSendCodeReq req){
        String code = RandomUtil.randomString(4);
//        CODE = code;
        UUID key = UUID.randomUUID();
        String keyStr = RedisConstants.LOGIN_CODE + key;
        redisService.setCacheObject(keyStr, code, RedisConstants.LOGIN_CODE_TIME, TimeUnit.SECONDS);
        LOG.info("生成短信验证码：{}",code);
        //保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
//        LOG.info("保存短信记录表！");
        return new LoginCodeResp(code, keyStr);
    }


    /**
     * 登录功能
     */
    public MemberLoginResp login(MemberLoginReq req){
        //将封装类中的属性拿出来，转为String类型
        String mobile = req.getMobile();
        String codeReq = req.getCode();
        String keyRedis = req.getKeyRedis();
        String result = redisService.getCacheObject(RedisConstants.LOGIN_CODE + keyRedis);

        Member memberDB = selectByMobile(mobile);

        if(ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_REGISTER);
        }

        if(result == null){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_NOT_EXIST);
        }

        //校验短信验证码
        if(!result.equals(codeReq)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        //ps：升级htull版本
        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }

    /**
     *查询mobile是否存在
     */
    private Member selectByMobile(String mobile) {
        //查询数据库中是否已经存在mobile
        //类似与where
        MemberExample memberExample = new MemberExample();
        //查询是否有mobile相同的例子
        memberExample.createCriteria().andMobileEqualTo(mobile);
        //根据例子查询出是否为空或者数组
        List<Member> list = memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }




    /**
     *生成短信验证码
     */
//    public void sendCode(MemberSendCodeReq req){
    //将封装类中的属性拿出来，转为String类型
//        String mobile = req.getMobile();

    //查询数据库中是否已经存在mobile
//        Member memberDB = selectByMobile(mobile);

//        if(ObjectUtil.isNull(memberDB)){
//            LOG.info("手机号不存在，插入一条数据！");
//            //封装插入用户记录
//            Member member = new Member();
//            //雪花算法
//            member.setId(SnowUtil.getSnowflakeNextId());
//            member.setMobile(mobile);
//            //Member record：传入一个member的对象
//            memberMapper.insert(member);
//        }else{
//            LOG.info("手机号已存在，不插入数据！");
//        }

    //生成验证码
//        String code = RandomUtil.randomString(4);
//        String code = "8888";
//        LOG.info("生成短信验证码：{}",code);
//
//
//        //保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
//        LOG.info("保存短信记录表！");
//
//        //对接短信通道，发送通道
//        LOG.info("对接短信通道！");
//    }

}
