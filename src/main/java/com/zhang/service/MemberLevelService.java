package com.zhang.service;

import com.zhang.api.user.ipo.ParamAddMemberLevel;
import com.zhang.api.user.ipo.ParamModMemberLevel;
import com.zhang.api.user.opo.RetMemberLevelObj;
import com.zhang.common.ReturnValue;
import com.zhang.common.ServiceResult;
import com.zhang.pojo.BMemberLevel;

import java.util.List;

/**
 * @Author: zhang
 * @Description: com.zhang.service
 * @Date：Created in 10:02 2021/2/8
 */
public interface MemberLevelService {
    /**
     * 获取全部的会员等级规则
     * @return 集合
     */
    ServiceResult<List<RetMemberLevelObj>> getMemberLevel();

    /**
     * 新增会员等级规则
     * @param param 新增参数
     * @return 等级规则ID
     */
    ServiceResult<BMemberLevel> addMemberLevel(ParamAddMemberLevel param);
    /**
     * 删除会员的等级规则
     * @param levelId 等级规则ID
     * @return 结果
     */
    ReturnValue delMemberLevel(Integer levelId);

    /**
     * 修改会员等级规则
     * @param param 修改的等级规则
     * @return 结果
     */
    ReturnValue updateMemberLevel(ParamModMemberLevel param);
}
