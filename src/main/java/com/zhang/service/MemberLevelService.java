package com.zhang.service;

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
    ServiceResult<List<BMemberLevel>> getMemberLevel();

    /**
     * 新增会员等级规则
     * @param bMemberLevel 新增参数
     * @return 等级规则ID
     */
    ServiceResult<BMemberLevel> addMemberLevel(BMemberLevel bMemberLevel);
    /**
     * 删除会员的等级规则
     * @param levelId 等级规则ID
     * @return 结果
     */
    ReturnValue delMemberLevel(Integer levelId);

    /**
     * 修改会员等级规则
     * @param bMemberLevel 修改的等级规则
     * @return 结果
     */
    ReturnValue updateMemberLevel(BMemberLevel bMemberLevel);
}
