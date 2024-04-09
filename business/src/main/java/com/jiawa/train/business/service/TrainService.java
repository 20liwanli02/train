package com.jiawa.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.train.common.resp.PageResp;
import com.jiawa.train.common.util.SnowUtil;
import com.jiawa.train.business.domain.Train;
import com.jiawa.train.business.domain.TrainExample;
import com.jiawa.train.business.mapper.TrainMapper;
import com.jiawa.train.business.req.TrainQueryReq;
import com.jiawa.train.business.req.TrainSaveReq;
import com.jiawa.train.business.resp.TrainQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private static final Logger LOG = LoggerFactory.getLogger(TrainService.class);

     @Resource
    private TrainMapper trainMapper;


    public void save(TrainSaveReq req){
        DateTime now = DateTime.now();
        Train train = BeanUtil.copyProperties(req, Train.class);
        if(ObjectUtil.isNull(train.getId())){
            train.setId(SnowUtil.getSnowflakeNextId());
            train.setCreateTime(now);
            train.setUpdateTime(now);
            trainMapper.insert(train);
        }else{
            train.setUpdateTime(now);
            trainMapper.updateByPrimaryKey(train);
        }

    }

    public PageResp<TrainQueryResp> queryList(TrainQueryReq req){
        TrainExample trainExample = new TrainExample();
        trainExample.setOrderByClause("id desc");
        TrainExample.Criteria criteria = trainExample.createCriteria();

        LOG.info("查询页码：{}",req.getPage());
        LOG.info("每页条数：{}",req.getSize());
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Train> trainlist = trainMapper.selectByExample(trainExample);

        PageInfo<Train> pageInfo = new PageInfo<>(trainlist);
        LOG.info("总行数：{}",pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());
        List<TrainQueryResp> list = BeanUtil.copyToList(trainlist, TrainQueryResp.class);
        PageResp<TrainQueryResp> PageResp = new PageResp<>();
        PageResp.setTotal(pageInfo.getTotal());
        PageResp.setList(list);
        return PageResp;
    }

    public void delete(Long id){
        trainMapper.deleteByPrimaryKey(id);
    }
}