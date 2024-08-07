package com.jiawa.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.train.business.domain.Station;
import com.jiawa.train.business.domain.StationExample;
import com.jiawa.train.business.mapper.StationMapper;
import com.jiawa.train.business.req.StationQueryReq;
import com.jiawa.train.business.req.StationSaveReq;
import com.jiawa.train.business.resp.StationQueryResp;
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
public class StationService {
    private static final Logger LOG = LoggerFactory.getLogger(StationService.class);

     @Resource
    private StationMapper stationMapper;


    public void save(StationSaveReq req){
        DateTime now = DateTime.now();
        Station station = BeanUtil.copyProperties(req, Station.class);
        if(ObjectUtil.isNull(station.getId())){
            // 保存之前，先校验唯一键是否存在
            //            Station stationDB = selectByUnique(req.getName());生成了一个stationDB对象
            Station stationDB = selectByUnique(req.getName());
            if(ObjectUtil.isNotEmpty(stationDB)){
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_STATION_NAME_UNIQUE_ERROR);
            }

            station.setId(SnowUtil.getSnowflakeNextId());
            station.setCreateTime(now);
            station.setUpdateTime(now);
            stationMapper.insert(station);
        }else{
            station.setUpdateTime(now);
            stationMapper.updateByPrimaryKey(station);
        }

    }

    private Station selectByUnique(String name) {
        StationExample stationExample = new StationExample();
        StationExample.Criteria criteria = stationExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Station> list = stationMapper.selectByExample(stationExample);
        if(CollUtil.isNotEmpty(list)){
//            throw new BusinessException(BusinessExceptionEnum.BUSINESS_STATION_NAME_UNIQUE_ERROR);
            return list.get(0);
        }else{
            return null;
        }
    }

    public PageResp<StationQueryResp> queryList(StationQueryReq req){
        StationExample stationExample = new StationExample();
        stationExample.setOrderByClause("id desc");
        StationExample.Criteria criteria = stationExample.createCriteria();

        LOG.info("查询页码：{}",req.getPage());
        LOG.info("每页条数：{}",req.getSize());
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Station> stationlist = stationMapper.selectByExample(stationExample);

        PageInfo<Station> pageInfo = new PageInfo<>(stationlist);
        LOG.info("总行数：{}",pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());
        List<StationQueryResp> list = BeanUtil.copyToList(stationlist, StationQueryResp.class);
        PageResp<StationQueryResp> PageResp = new PageResp<>();
        PageResp.setTotal(pageInfo.getTotal());
        PageResp.setList(list);
        return PageResp;
    }

    public void delete(Long id){
        stationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有车站信息
     */
    public List<StationQueryResp> queryAll(){
        StationExample stationExample = new StationExample();
        stationExample.setOrderByClause("name_pinyin asc");
        List<Station> stationlist = stationMapper.selectByExample(stationExample);
        return BeanUtil.copyToList(stationlist, StationQueryResp.class);
    }
}