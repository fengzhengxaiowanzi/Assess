package com.zhang.controller;

import com.alibaba.fastjson.JSON;
import com.zhang.dto.JsonMessage;
import com.zhang.util.RsaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhang
 * @Description: com.zhang.controller
 * @Date：Created in 13:12 2021/1/7
 */
@RestController
public class RsaController {
    @Resource
    private RsaUtil rsaUtil;
    @GetMapping("rsaGet.do")
    public JsonMessage getRsa(){
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.getData().put("rsa",rsaUtil.getPublicKey());
        jsonMessage.setId(1);
        return jsonMessage;
    }
}
