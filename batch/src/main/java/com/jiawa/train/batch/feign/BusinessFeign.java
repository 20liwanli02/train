package com.jiawa.train.batch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient("business")，已经注册中心的情况下这样写
@FeignClient(name = "business",url = "http//127.0.0.1:8002/business")
public interface BusinessFeign {
    @GetMapping("/hello")
    String hello();
}
