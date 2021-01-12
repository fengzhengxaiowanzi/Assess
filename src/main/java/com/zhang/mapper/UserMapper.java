package com.zhang.mapper;

import com.zhang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zhang
 * @Description: com.zhang.mapper
 * @Date：Created in 14:45 2021/1/6
 */
public interface UserMapper {

    /**
     * 用户登录
     *
     * @param name 用户名
     * @param pass 密码
     * @return 实例对象
     */
    User queryLogin(String name,String pass);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int insert(User tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int update(User tUser);

    /**
     * 重名检查
     *
     * @param account 用户名
     * @return 数量
     */
    int queryByAccount(String account);
    /**
     * 邮箱检查
     *
     * @param email 用户名
     * @return 数量
     */
    int queryByEmail(String email);
    /**
     * 手机号检查
     *
     * @param phone 用户名
     * @return 数量
     */
    int queryByPhone(Long phone);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    /**
     * 修改重名检查
     *
     * @param account 用户名
     * @param id 用户id
     * @return 数量
     */
    int queryByModAccount(String account,Long id);
    /**
     * 修改邮箱检查
     *
     * @param email 用户名
     * @param id 用户id
     * @return 数量
     */
    int queryByModEmail(String email,Long id);
    /**
     * 修改手机号检查
     *
     * @param phone 用户名
     * @param id 用户id
     * @return 数量
     */
    int queryByModPhone(Long phone,Long id);

    /**
     分页统计
     * @param account  用户名
     * @param phone 手机号
     * @param startTime 生日开始时间
     * @param endTime 生日结束时间
     * @param email 邮箱
     * @return 数据条数
     */
    int selectLikeFrontUserCount(String account, String phone, String startTime, String endTime, String email);
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
    List<User> selectLikeFrontUser(String account, String phone, String startTime, String endTime, String email,Integer offset,Integer limit);
}