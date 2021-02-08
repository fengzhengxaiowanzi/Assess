package com.zhang.api.user;

import com.alibaba.fastjson.JSONObject;
import com.zhang.api.user.ipo.ParamAddMember;
import com.zhang.common.DictCode;
import com.zhang.common.ServiceResult;
import com.zhang.pojo.BMemberLevel;
import com.zhang.service.MemberLevelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhang
 * @Description: com.zhang.api.user
 * @Date：Created in 10:01 2021/2/8
 */
@RestController
@RequestMapping("/api/v1/member")

public class MemberLevelApi {
    @Resource
    private MemberLevelService memberLevelService;
    /**
     * 注册 - 用户账号
     *
     * @author chenyue
     * @date 2020年4月10日
     */
    @PostMapping("/addMember")
    public ServiceResult<String> userRegister(@RequestBody ParamAddMember param) {
        BMemberLevel bMemberLevel = new BMemberLevel();
        bMemberLevel.setLevelName(param.getLevelName());
        bMemberLevel.setLevelDistCount(param.getLevelDistCount());
        bMemberLevel.setLevelText(param.getLevelText());
        bMemberLevel.setPointMin(param.getPointMin());
        bMemberLevel.setPointMax(param.getPointMax());
        ServiceResult<BMemberLevel> rs =
                memberLevelService.addMemberLevel(bMemberLevel);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("levelId",rs.getResult().getLevelId());
        if(rs.getCode() == DictCode.SUCCESS){
            return new ServiceResult<String>(DictCode.SUCCESS,"新增成功").setResult(jsonObject.toJSONString());
        }
        return new ServiceResult<String>(rs.getCode(),rs.getMessage());
    }
    @PostMapping("/test")
    public String test(){
        return "test";
    }
}
