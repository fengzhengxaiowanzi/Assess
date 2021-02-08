package com.zhang.common;

/**********************************************
 *  <br>
 *
 * @author pengcheng<br>
 * @version 1.0.0<br>
 * createTime		2020年03月20日16:05<br>
 *
 ***********************************************/
public class ServiceResult<T> extends
        ReturnValue {
    /**
     *
     */
    private static final long serialVersionUID = 4044676291787860847L;
    /**
     * 结果
     */
    private T result;

    public ServiceResult() {
    }

    public ServiceResult(Integer code, String message) {
        super(code, message);
    }

    public T getResult() {
        return result;
    }

    public ServiceResult<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public ServiceResult(Integer code, String message, T result) {
        super(code, message);
        this.result = result;
    }
}
