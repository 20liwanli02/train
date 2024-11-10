package com.jiawa.train.business.req;

import com.jiawa.train.common.req.PageReq;

public class TrainQueryReq extends PageReq {

//    private String trainCode;

//    public String getTrainCode() {
//        return trainCode;
//    }
//
//    public void setTrainCode(String trainCode) {
//        this.trainCode = trainCode;
//    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrainQueryReq{");
//        sb.append("trainCode='").append(trainCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
