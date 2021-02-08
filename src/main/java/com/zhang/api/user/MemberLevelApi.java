package com.zhang.api.user;

import com.alibaba.fastjson.JSONObject;
import com.zhang.api.user.ipo.ParamAddMemberLevel;
import com.zhang.api.user.ipo.ParamDelMemberLevel;
import com.zhang.api.user.ipo.ParamModMemberLevel;
import com.zhang.api.user.opo.RetMemberLevelObj;
import com.zhang.common.DictCode;
import com.zhang.common.ReturnValue;
import com.zhang.common.ServiceResult;
import com.zhang.pojo.BMemberLevel;
import com.zhang.service.MemberLevelService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
     * 新增会员等级规则
     * @param param 新增参数
     * @return
     */
    @PostMapping("/addMemberLevel")
    public ServiceResult<String> addMemberLevel(@RequestBody
                                                        ParamAddMemberLevel param) {
        ServiceResult<BMemberLevel> rs =
                memberLevelService.addMemberLevel(param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("levelId",rs.getResult().getLevelId());
        if(rs.getCode() == DictCode.SUCCESS){
            return new ServiceResult<String>(DictCode.SUCCESS,"新增成功").setResult(jsonObject.toJSONString());
        }
        return new ServiceResult<String>(rs.getCode(),rs.getMessage());
    }

    /**
     * 修改会员等级规则
     * @param param 修改参数
     * @return
     */
    @PostMapping("/updateMemberLevel")
    public ServiceResult<String> updateMemberLevel(@RequestBody
                                                           ParamModMemberLevel param){
        ReturnValue rs =
                memberLevelService.updateMemberLevel(param);
        if(rs.getCode() == DictCode.SUCCESS){
            return new ServiceResult<String>(DictCode.SUCCESS,"修改成功");
        }
        return new ServiceResult<String>(rs.getCode(),rs.getMessage());
    }
    @DeleteMapping("/deleteMemberLevel")
    public ServiceResult<String> deleteMemberLevel(@RequestBody
                                                   ParamDelMemberLevel param){
        ReturnValue rs =
                memberLevelService.delMemberLevel(param.getLevelId());
        if(rs.getCode() == DictCode.SUCCESS){
            return new ServiceResult<String>(DictCode.SUCCESS,"删除成功");
        }
        return new ServiceResult<String>(rs.getCode(),rs.getMessage());
    }
    @GetMapping("/getMemberLevel")
    public ServiceResult<List<RetMemberLevelObj>> getMemberLevel(){
        return memberLevelService.getMemberLevel();
    }
}
