package com.jiawa.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.train.business.domain.Train;
import com.jiawa.train.business.domain.TrainExample;
import com.jiawa.train.business.mapper.TrainMapper;
import com.jiawa.train.business.req.TrainQueryReq;
import com.jiawa.train.business.req.TrainSaveReq;
import com.jiawa.train.business.resp.TrainQueryResp;
import com.jiawa.train.common.exception.BusinessException;
import com.jiawa.train.common.exception.BusinessExceptionEnum;
import com.jiawa.train.common.resp.PageResp;
import com.jiawa.train.common.util.SnowUtil;
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

            // 保存之前，先校验唯一键是否存在
            Train trainDB = selectByUnique(req.getCode());
            if (ObjectUtil.isNotEmpty(trainDB)) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_TRAIN_CODE_UNIQUE_ERROR);
            }

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
        trainExample.setOrderByClause("code asc");
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

    /**
     * 查询所有车次
     */
    // @Transactional // mybatis 一级缓存，这个方法没有结束，事务就不会提交
    public List<TrainQueryResp> queryAll(){
        List<Train> trainlist = selectAll();
        return BeanUtil.copyToList(trainlist, TrainQueryResp.class);
    }

    public List<Train> selectAll() {
        TrainExample trainExample = new TrainExample();
        trainExample.setOrderByClause("code asc");
        return trainMapper.selectByExample(trainExample);
    }

    private Train selectByUnique(String Code) {
        TrainExample trainExample = new TrainExample();
        TrainExample.Criteria criteria = trainExample.createCriteria();
        criteria.andCodeEqualTo(Code);
        List<Train> list = trainMapper.selectByExample(trainExample);
        if(CollUtil.isNotEmpty(list)){
//            throw new BusinessException(BusinessExceptionEnum.BUSINESS_STATION_NAME_UNIQUE_ERROR);
            return list.get(0);
        }else{
            return null;
        }
    }
}