package com.zhang.common;

/**********************************************
 * 状态码 <br>
 *
 * @author		pengcheng<br>
 * @version		0.0.1-SNAPSHOT<br>
 * 创建时间	2018年9月27日下午4:10:59<br>
 *
 ***********************************************/
public class DictCode {
    /** 成功 */
    public static final int SUCCESS = 10000;
    /** 超时异常（网关在超时时间内未等到应用系统返回结果） */
    public static final int RESPONSE_ERROR = 30000;

    /*
     * ********************* 通用的错误代码Code范围 -1到-19999 ********************
     */
    /** 失败 */
    public static final int FAIL = -9999;
    /** 业务结果未知，等待结果信息 */
    public static final int RESULT_WAIT = -50;
    /** 执行重试失败 */
    public static final int RETRY_FAIL = -100;
    /** 业务中用于表达信息不存在的场景 */
    public static final int BUSINFO_NOT_EXIST = -103;
    /** 业务中用于表达信息已存在(已重复)的场景 */
    public static final int BUSINFO_EXIST = -104;
    /** 表单验证失败（不通过） */
    public static final int FORM_VALID_FAIL = -199;
    /** 失败，请求已经被处理 */
    public static final int REPEAT_REQ = -1000;
    /** 异常 */
    public static final int EXCEPTION = -14444;
    /** SQL异常 */
    public static final int SQL_EXCEPTION = -14444;

    /** 参数解析错误 */
    public static final int PARAM_PARSE_FAIL = -10001;
    /** 参数验证失败 */
    public static final int PARAM_VALIDATE_FAIL = -10002;
    /** 参数空值错误 */
    public static final int PARAM_NULL_ERROR = -10003;
    /** 参数格式错误 */
    public static final int PARAM_FORMAT_ERROR = -10004;
    /** 对象格式化错误 */
    public static final int BEAN_FORMAT_ERROR = -10011;

    /** 参数解析错误 */
    public static final int EXCEPTION_PARAM_PARSE = -14445;

    /** 业务逻辑结果未知 */
    public static final int UNKNOWN_RESULT = -19999;

    /*
     * ********************* web通用的错误代码Code范围 -90000到-100000 ********************
     */
    /** 未授权 */
    public static final int UNAUTH = -90000;
}
