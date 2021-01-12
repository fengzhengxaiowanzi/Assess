package com.zhang.service;

import com.zhang.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhang
 * @Description: com.zhang.service
 * @Date：Created in 14:00 2021/1/6
 */

public interface UserManService {
    /**
     *  用户登录
     * @param name 用户名
     * @param pass 密码
     * @return 登录用户对象
     */
    User userLogin(String name,String pass);

    /**
     *  用户注册
     * @param user 用户注册信息
     * @return 用户注册结果
     *@throws Exception 重名异常
     */
    Boolean userReg(User user) throws Exception;

    /**
     * 根据主键获得用户数据
     * @param id 主键
     * @return 用户数据
     */
    User userGet(Long id);
    /**
     * 修改用户数据
     * @param user 用户信息
     * @return 结果
     * @throws Exception 重名异常
     */
    Boolean userModInfo(User user) throws Exception;
    /**
     * 修改用户数据
     * @param id 用户id
     * @param oldPass 原先密码
     * @param newPass 修改后密码
     * @return 结果
     * @throws Exception 重名异常
     */
    Boolean userModPass(Long id,String oldPass,String newPass) throws Exception;

    /**
     *  分页查询
     * @param account  用户名
     * @param phone 手机号
     * @param startTime 生日开始时间
     * @param endTime 生日结束时间
     * @param email 邮箱
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 结果集
     */
    List<User> frontUserShow(String account, String phone, String startTime, String endTime, String email, Integer offset, Integer limit);

    /**
     分页统计
     * @param account  用户名
     * @param phone 手机号
     * @param startTime 生日开始时间
     * @param endTime 生日结束时间
     * @param email 邮箱
     * @return 数据条数
     */
    int frontUserShowCount(String account, String phone, String startTime, String endTime, String email);

    /**
     * 删除操作
     * @param id 用户id
     * @return 结果
     */
    Boolean userDel(Long id);
    /**
     * 重置密码操作
     * @param id 用户id
     * @return 结果
     */
    Boolean userReset(Long id);
}
