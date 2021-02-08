package com.zhang.common;

/**********************************************
 * 数据库 <br>
 *
 * @author		pengcheng<br>
 * @version		0.0.1-SNAPSHOT<br>
 * 创建时间	2018年9月27日下午3:49:03<br>
 *
 ***********************************************/
public class DictDb {
    /**********************************************
     * 数据库连接超时时间 <br>
     *
     * @author		pengcheng<br>
     * @version		0.0.1-SNAPSHOT<br>
     * 创建时间	2018年9月27日下午4:49:48<br>
     *
     ***********************************************/
    public static class ConnTimeout {
        /**
         * 常量-数据库处理超时：3秒
         */
        public static final int DB_CONN_TIMEOUT_3000 = 3000;
        /**
         * 常量-数据库处理超时：5秒
         */
        public static final int DB_CONN_TIMTOUT_5000 = 5000;
        /**
         * 常量-数据库处理超时：8秒
         */
        public static final int DB_CONN_TIMEOUT_8000 = 8000;
    }
    /**********************************************
     * 数据库的逻辑删除标志位 <br>
     *
     * @author		pengcheng<br>
     * @version		0.0.1-SNAPSHOT<br>
     * 创建时间	2018年9月27日下午3:38:36<br>
     *
     ***********************************************/
    public static class Status {
        /**
         * 数据库表记录逻辑删除标志（1：表示正常）
         */
        public static final int NORMAL = 1;
        /**
         * 数据库表记录逻辑删除标志（-1：表示已删除）
         */
        public static final int UN_NORMAL = -1;
    }

    /**********************************************
     * 数据库是否标识符 <br>
     *
     * @author		pengcheng<br>
     * @version		0.0.1-SNAPSHOT<br>
     * 创建时间	2018年9月27日下午3:38:36<br>
     *
     ***********************************************/
    public static class IsOrNot {
        /**
         * 数据库是否标识符（1：是）
         */
        public static final int YES = 1;
        /**
         * 数据库是否标识符（-1：否）
         */
        public static final int NOT = -1;
    }
    /**********************************************
     * 排序规则 <br>
     *
     * @author		pengcheng<br>
     * @version		1.0.0<br>
     * createTime	2020/4/3  11:19 <br>
     *
     ***********************************************/
    public static class OrderRule {
        /**
         * 正序
         */
        public static final int ASC = 1;
        /**
         * 倒序
         */
        public static final int DESC = 2;

        /**
         * 格式化
         * @param orderRule
         * @return
         */
        public static String format(Integer orderRule) {
            if (null == orderRule) {
                return "asc";
            }
            switch (orderRule) {
                case ASC:
                    return "asc";
                case DESC:
                    return "desc";
                default:
                    return "asc";
            }
        }
    }
}
