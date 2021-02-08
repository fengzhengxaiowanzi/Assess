package com.zhang.api.user.ipo;

import lombok.Data;

/**
 * @Author: zhang
 * @Description: com.zhang.api.user.ipo
 * @Date：Created in 15:12 2021/2/8
 */
@Data
public class ParamModMemberLevel {
    /**
     * 会员等级ID
     */
    private Integer levelId;
    /**
     * 等级名称
     */
    private String levelName;
    /**
     * 等级分数上限
     */
    private Integer pointMax;
    /**
     * 等级分数下限
     */
    private Integer pointMin;
    /**
     * 折扣率 单位百分之
     */
    private Integer levelDistCount;
    /**
     * 等级规则
     */
    private String levelText;
}
