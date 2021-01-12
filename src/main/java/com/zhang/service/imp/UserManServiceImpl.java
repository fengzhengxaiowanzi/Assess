package com.zhang.service.imp;

import com.zhang.mapper.UserMapper;
import com.zhang.pojo.User;
import com.zhang.service.UserManService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zhang
 * @Description: com.zhang.service.imp
 * @Date：Created in 14:48 2021/1/6
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserManServiceImpl implements UserManService {
    @Resource
    private UserMapper userMapper;
    @Resource
    @Value("${defaultPass}")
    private String defaultPass;
    @Override
    public User userLogin(String name, String pass) {
        return userMapper.queryLogin(name,pass);
    }

    @Override
    public Boolean userReg(User user) throws Exception {
        boolean flag = false;
        flag = userMapper.queryByAccount(user.getUserAccount()) == 0;
        if(!flag){
            throw new Exception("用户名重复");
        }
        flag = userMapper.queryByEmail(user.getUserEmail()) == 0;
        if(!flag){
            throw new Exception("邮箱重复");
        }
        flag = userMapper.queryByPhone(user.getUserPhone()) == 0;
        if(!flag){
            throw new Exception("手机号重复");
        }
        flag =userMapper.insert(user) > 0;
        return flag;
    }

    @Override
    public User userGet(Long id) {
        return userMapper.queryById(id);
    }

    @Override
    public Boolean userModInfo(User user) throws Exception {
        boolean flag = false;
        flag = userMapper.queryByModAccount(user.getUserAccount(),user.getId()) == 0;
        if(!flag){
            throw new Exception("用户名重复");
        }
        flag = userMapper.queryByModEmail(user.getUserEmail(),user.getId()) == 0;
        if(!flag){
            throw new Exception("邮箱重复");
        }
        flag = userMapper.queryByModPhone(user.getUserPhone(),user.getId()) == 0;
        if(!flag){
            throw new Exception("手机号重复");
        }
        flag =userMapper.update(user) > 0;
        return flag;
    }
    @Override
    public Boolean userModPass(Long id,String oldPass,String newPass) throws Exception {
        boolean flag = false;
        User user = userMapper.queryById(id);
        if(!oldPass.equals(user.getUserPass())){
            throw new Exception("原密码输入错误");
        }
        User newUser = new User();
        newUser.setId(id);
        newUser.setUserPass(newPass);
        flag = userMapper.update(newUser) > 0;
        return flag;
    }

    @Override
    public List<User> frontUserShow(String account, String phone, String startTime, String endTime, String email, Integer offset, Integer limit) {
        return userMapper.selectLikeFrontUser(account,phone,startTime,endTime,email,offset,limit);
    }

    @Override
    public int frontUserShowCount(String account, String phone, String startTime, String endTime, String email) {
      return userMapper.selectLikeFrontUserCount(account,phone,startTime,endTime,email);
    }

    @Override
    public Boolean userDel(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean userReset(Long id) {
        User user = new User();
        user.setId(id);
        user.setUserPass(DigestUtils.md5Hex(defaultPass));
        return userMapper.update(user) > 0;
    }

}
