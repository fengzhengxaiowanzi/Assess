package com.zhang.mapper;
import com.zhang.pojo.BMemberLevel;

import java.util.List;

/**
 * @Author: zhang
 * @Description: com.yihu.mapper
 * @Date：Created in 15:24 2021/2/7
 */
public interface BMemberLevelMapper {
    /**
     * 获取会员等级规则
     * @return 会员等级规则
     */
    List<BMemberLevel> selectAllLevel();

    /**
     * 新增会员等级
     * @param bMemberLevel 等级数据
     * @return 结果
     */
    Integer insertLevel(BMemberLevel bMemberLevel);

    /**
     *更新会员等级
     * @param bMemberLevel 等级数据
     * @return 结果
     */
    Integer updateLevel(BMemberLevel bMemberLevel);
}
