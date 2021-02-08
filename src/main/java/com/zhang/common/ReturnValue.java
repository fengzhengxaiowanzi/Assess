package com.zhang.common;

import java.io.Serializable;

/**********************************************
 *  <br>
 *
 * @author pengcheng<br>
 * @version 1.0.0<br>
 * createTime		2020年03月20日16:03<br>
 *
 ***********************************************/
public class ReturnValue implements Serializable {

    private static final long serialVersionUID = 244235751449882272L;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;

    public ReturnValue() {
    }

    public ReturnValue(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReturnValue(
            ReturnValue rtn) {
        this.code = rtn.getCode();
        this.message = rtn.getMessage();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
