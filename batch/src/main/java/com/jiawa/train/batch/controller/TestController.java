package com.jiawa.train.batch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

//    @Resource
//    BusinessFeign businessFeign;

    @GetMapping("/hello")
    public String hello() {
//        String businessHello = businessFeign.hello();
//        LOG.info(businessHello);
//        return "Hello World! Batch! " + businessHello;
        return "Hello World! Batch! ";
    }
}
