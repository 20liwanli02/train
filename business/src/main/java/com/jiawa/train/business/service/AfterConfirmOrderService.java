package com.jiawa.train.business.service;

import com.jiawa.train.business.domain.DailyTrainSeat;
import com.jiawa.train.business.domain.DailyTrainTicket;
import com.jiawa.train.business.mapper.DailyTrainSeatMapper;
import com.jiawa.train.business.mapper.cust.DailyTrainTicketMapperCust;
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
     @Resource
     private DailyTrainTicketMapperCust dailyTrainTicketMapperCust;

     @Transactional
    public void AfterDoConfirm(DailyTrainTicket dailyTrainTicket, List<DailyTrainSeat> finalSeatList){
         for (DailyTrainSeat dailyTrainSeat : finalSeatList) {
             DailyTrainSeat seatForUpdate = new DailyTrainSeat();
             seatForUpdate.setId(dailyTrainSeat.getId());
             seatForUpdate.setSell(dailyTrainSeat.getSell());
             seatForUpdate.setUpdateTime(new Date());
             dailyTrainSeatMapper.updateByPrimaryKeySelective(seatForUpdate);

             Integer startIndex = dailyTrainTicket.getStartIndex();
             Integer endIndex = dailyTrainTicket.getEndIndex();
             char[] chars = seatForUpdate.getSell().toCharArray();
             Integer maxStartIndex = endIndex - 1;
             Integer minEndIndex = startIndex + 1;
             Integer minStartIndex = 0;
             for (int i = startIndex - 1; i >= 0 ; i--) {
                 char aChar = chars[i];
                 if(aChar == '1'){
                     minStartIndex = i + 1;
                     break;
                 }
             }
             LOG.info("影响出发站区间：" + minStartIndex + "-" + maxStartIndex);
             Integer maxEndIndex = seatForUpdate.getSell().length();
             for (int i = endIndex; i < seatForUpdate.getSell().length(); i++) {
                 char aChar = chars[i];
                 if (aChar == '1') {
                     maxEndIndex = i;
                     break;
                 }
             }
             LOG.info("影响到达站区间：" + minEndIndex + "-" + maxEndIndex);
             dailyTrainTicketMapperCust.updateCountBySell(
                     dailyTrainSeat.getDate(),
                     dailyTrainSeat.getTrainCode(),
                     dailyTrainSeat.getSeatType(),
                     minStartIndex,
                     maxStartIndex,
                     minEndIndex,
                     maxEndIndex);
         }
    }
}