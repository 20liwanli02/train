package com.jiawa.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.train.common.context.LoginMemberContext;
import com.jiawa.train.common.resp.PageResp;
import com.jiawa.train.common.util.SnowUtil;
import com.jiawa.train.member.domain.Passenger;
import com.jiawa.train.member.domain.PassengerExample;
import com.jiawa.train.member.mapper.PassengerMapper;
import com.jiawa.train.member.req.PassengerQueryReq;
import com.jiawa.train.member.req.PassengerSaveReq;
import com.jiawa.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper passengerMapper;


    /**
     *增加乘车人信息
     */
    public void save(PassengerSaveReq req){
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        if(ObjectUtil.isNull(passenger.getId())){
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        }else{
            passenger.setUpdateTime(now);
            passengerMapper.updateByPrimaryKey(passenger);
        }

    }

    /**
     *查询乘车人信息
     */
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req){
        PassengerExample passengerExample = new PassengerExample();
        passengerExample.setOrderByClause("id desc");
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if(ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(LoginMemberContext.getId());
        }
        LOG.info("查询页码：{}",req.getPage());
        LOG.info("每页条数：{}",req.getSize());
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Passenger> passengerlist = passengerMapper.selectByExample(passengerExample);
        //类似于生成了一个查询总数的sql语句
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengerlist);
        LOG.info("总行数：{}",pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());
        List<PassengerQueryResp> list = BeanUtil.copyToList(passengerlist, PassengerQueryResp.class);
        PageResp<PassengerQueryResp> PageResp = new PageResp<>();
        PageResp.setTotal(pageInfo.getTotal());
        PageResp.setList(list);
        return PageResp;
    }

    /**
     * 删除乘车人信息
     */
    public void delete(Long id){
        passengerMapper.deleteByPrimaryKey(id);
    }

}
