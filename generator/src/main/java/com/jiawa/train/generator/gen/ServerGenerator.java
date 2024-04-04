package com.jiawa.train.generator.gen;

import com.jiawa.train.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {

    static String toPath = "generator/src/main/java/com/jiawa/train/generator/test/";

    public static void main(String[] args) throws IOException, TemplateException{
        FreemarkerUtil.initConfig("test.ftl");
        Map<String,Object> param = new HashMap<>();
        param.put("domain","Test");
        FreemarkerUtil.generator(toPath+"Test.java",param);
    }
}
