package com.zhang.util;

/**
 * @Author: zhang
 * @Description: com.zhang.util
 * @Date：Created in 15:16 2021/1/8
 */
public class CheckUtil {
    public  static  String checkNull(String str){
        if(str == null || "".equals(str)){
            str = null;
        }
        return str;
    }
}
