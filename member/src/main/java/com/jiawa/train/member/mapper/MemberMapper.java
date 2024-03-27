package com.jiawa.train.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    /**
     *查询member表的所以有记录数
     */
    int count();
}
