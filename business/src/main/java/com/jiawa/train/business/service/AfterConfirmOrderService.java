package com.jiawa.train.business.service;

import com.jiawa.train.business.domain.DailyTrainSeat;
import com.jiawa.train.business.domain.DailyTrainTicket;
import com.jiawa.train.business.feign.MemberFeign;
import com.jiawa.train.business.mapper.DailyTrainSeatMapper;
import com.jiawa.train.business.mapper.cust.DailyTrainTicketMapperCust;
import com.jiawa.train.business.req.ConfirmOrderTicketReq;
import com.jiawa.train.common.context.LoginMemberContext;
import com.jiawa.train.common.req.MemberTicketReq;
import com.jiawa.train.common.resp.CommonResp;
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
    @Resource
    private MemberFeign memberFeign;

     @Transactional
    public void AfterDoConfirm(DailyTrainTicket dailyTrainTicket, List<DailyTrainSeat> finalSeatList, List<ConfirmOrderTicketReq> tickets){
         for (int j = 0, finalSeatListSize = finalSeatList.size(); j < finalSeatListSize; j++) {
             DailyTrainSeat dailyTrainSeat = finalSeatList.get(j);
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
             for (int i = startIndex - 1; i >= 0; i--) {
                 char aChar = chars[i];
                 if (aChar == '1') {
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

             // 调用会员服务接口，为会员增加一张车票
             MemberTicketReq memberTicketReq = new MemberTicketReq();
             memberTicketReq.setMemberId(LoginMemberContext.getId());
             memberTicketReq.setPassengerId(tickets.get(j).getPassengerId());
             memberTicketReq.setPassengerName(tickets.get(j).getPassengerName());
             memberTicketReq.setTrainDate(dailyTrainTicket.getDate());
             memberTicketReq.setTrainCode(dailyTrainTicket.getTrainCode());
             memberTicketReq.setCarriageIndex(dailyTrainSeat.getCarriageIndex());
             memberTicketReq.setSeatRow(dailyTrainSeat.getRow());
             memberTicketReq.setSeatCol(dailyTrainSeat.getCol());
             memberTicketReq.setStartStation(dailyTrainTicket.getStart());
             memberTicketReq.setStartTime(dailyTrainTicket.getStartTime());
             memberTicketReq.setEndStation(dailyTrainTicket.getEnd());
             memberTicketReq.setEndTime(dailyTrainTicket.getEndTime());
             memberTicketReq.setSeatType(dailyTrainSeat.getSeatType());
             CommonResp<Object> commonResp = memberFeign.save(memberTicketReq);
             LOG.info("调用member接口，返回：{}", commonResp);
         }
    }
}