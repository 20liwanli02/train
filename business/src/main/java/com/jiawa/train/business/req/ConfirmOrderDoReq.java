package com.jiawa.train.business.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class ConfirmOrderDoReq {

    @NotNull(message = "��Աid不能为空�?")
    private Long memberId;


    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @NotNull(message = "����不能为空�?")
    private Date date;


    @NotBlank(message = "���α��不能为空�?")
    private String trainCode;


    @NotBlank(message = "����վ不能为空�?")
    private String start;


    @NotBlank(message = "����վ不能为空�?")
    private String end;


    @NotNull(message = "��ƱID不能为空�?")
    private Long dailyTrainTicketId;


    @NotBlank(message = "��Ʊ不能为空�?")
    private List<ConfirmOrderTicketReq> tickets;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getDailyTrainTicketId() {
        return dailyTrainTicketId;
    }

    public void setDailyTrainTicketId(Long dailyTrainTicketId) {
        this.dailyTrainTicketId = dailyTrainTicketId;
    }

    public List<ConfirmOrderTicketReq> getTickets() {
        return tickets;
    }

    public void setTickets(List<ConfirmOrderTicketReq> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ConfirmOrderDoReq{");
        sb.append("memberId=").append(memberId);
        sb.append(", date=").append(date);
        sb.append(", trainCode='").append(trainCode).append('\'');
        sb.append(", start='").append(start).append('\'');
        sb.append(", end='").append(end).append('\'');
        sb.append(", dailyTrainTicketId=").append(dailyTrainTicketId);
        sb.append(", tickets=").append(tickets);
        sb.append('}');
        return sb.toString();
    }
}
