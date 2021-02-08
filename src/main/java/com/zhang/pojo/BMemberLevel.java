package com.zhang.pojo;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @Author: zhang
 * @Description: com.yihu.pojo
 * @Date：Created in 15:11 2021/2/7
 */
@Data
public class BMemberLevel {
    /**
     * 等级ID
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
    /**
     * 插入时间
     */
    private Timestamp insertTime;
    /**
     * 修改时间
     */
    private Timestamp lastModify;
    /**
     * 状态 1 有效 -1 无效
     */
    private Integer status;
}
