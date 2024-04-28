package com.jiawa.train.business.service;

import com.jiawa.train.business.domain.DailyTrainSeat;
import com.jiawa.train.business.mapper.DailyTrainSeatMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AfterConfirmOrderService {
    private static final Logger LOG = LoggerFactory.getLogger(AfterConfirmOrderService.class);

     @Resource
     private DailyTrainSeatMapper dailyTrainSeatMapper;

     @Transactional
    public void AfterDoConfirm(List<DailyTrainSeat> finalSeatList){
         for (DailyTrainSeat dailyTrainSeat : finalSeatList) {
             DailyTrainSeat seatForUpdate = new DailyTrainSeat();
             seatForUpdate.setId(dailyTrainSeat.getId());
             seatForUpdate.setSell(dailyTrainSeat.getSell());
             seatForUpdate.setUpdateTime(new Date());
             dailyTrainSeatMapper.updateByPrimaryKeySelective(seatForUpdate);
         }
    }
}