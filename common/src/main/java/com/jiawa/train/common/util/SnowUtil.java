package com.jiawa.train.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * 封装hutool雪花算法
 * 1.和时间戳有关，所以id递增
 * 2.机器不同，相同时间也不会产生相同的id
 * 3.同时生成的id有限，多了阻塞
 */
public class SnowUtil {

    private static long dataCenterId = 1;  //数据中心
    private static long workerId = 1;     //机器标识

    public static long getSnowflakeNextId() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextId();
    }

    public static String getSnowflakeNextIdStr() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextIdStr();
    }
}
