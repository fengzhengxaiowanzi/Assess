package com.zhang.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.zhang.common.ServiceResult;
import com.zhang.dto.JsonMessage;
import com.zhang.pojo.BMemberLevel;
import com.zhang.service.MemberLevelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.plaf.basic.BasicMenuBarUI;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: zhang
 * @Description: com.zhang.service.imp
 * @Date：Created in 10:45 2021/2/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.zhang.main.Application.class)
public class MemberLevelServiceImplTest {
    @Resource
    MemberLevelService memberLevelService;
    @Test
    public void getMemberLevel() {
        System.err.println("GET");
        ServiceResult<List<BMemberLevel>> rs = memberLevelService.getMemberLevel();
        System.out.println(JSON.toJSONString(rs));
    }

    @Test
    public void addMemberLevel() {
        BMemberLevel bMemberLevel = new BMemberLevel();
        bMemberLevel.setLevelDistCount(100);
        bMemberLevel.setPointMax(400);
        bMemberLevel.setPointMin(300);
        bMemberLevel.setLevelName("绿牌会员");
        bMemberLevel.setLevelText("绿牌规则");
        System.err.println(JSON.toJSONString(memberLevelService.addMemberLevel(bMemberLevel)));
    }

    @Test
    public void delMemberLevel() {
        System.err.println("DEL");
        System.err.println(JSON.toJSONString(memberLevelService.delMemberLevel(4)));
    }

    @Test
    public void updateMemberLevel() {
        BMemberLevel bMemberLevel = new BMemberLevel();
        bMemberLevel.setLevelText("删除");
        bMemberLevel.setLevelDistCount(102);
        bMemberLevel.setLevelName("删除信息");
        bMemberLevel.setLevelId(4);
        System.err.println(JSON.toJSONString(memberLevelService.updateMemberLevel(bMemberLevel)));
    }
}
