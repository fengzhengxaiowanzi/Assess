package com.zhang.api.user.opo;

import lombok.Data;

/**
 * 用户登录出参
 *
 * @author chenyue
 * @date: 2020年4月20日
 */
@Data
public class RetMemberLevelObj {
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
