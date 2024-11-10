package com.jiawa.train.business.req;

import com.jiawa.train.common.req.PageReq;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class DailyTrainTicketQueryReq extends PageReq {

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    /**
     * 车次编号
     */
    private String trainCode;

    //增加两个查询条件：出发站、到达站
    /**
     * 出发站
     */
    private String start;

    /**
     * 到达站
     */
    private String end;

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

    // 内置缓存的key和equals和hashCode方法有关
    // 包括本类和父类的属性，否则查询其他页也会从缓存中获取
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyTrainTicketQueryReq that)) return false;
        return Objects.equals(date, that.date) && Objects.equals(trainCode, that.trainCode) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(((DailyTrainTicketQueryReq) o).getPage(), that.getPage()) && Objects.equals(((DailyTrainTicketQueryReq) o).getSize(), that.getSize());
    }

    // 内置缓存
    @Override
    public int hashCode() {
        return Objects.hash(date, trainCode, start, end, getPage(), getSize());
    }

    @Override
    public String toString() {
        return "DailyTrainTicketQueryReq{" +
                "date=" + date +
                ", trainCode='" + trainCode + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                "} " + super.toString();
    }
}
