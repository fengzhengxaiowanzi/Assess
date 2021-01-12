package com.zhang.controller;

import com.alibaba.fastjson.JSON;
import com.zhang.anno.Auth;
import com.zhang.dto.JsonMessage;
import com.zhang.pojo.User;
import com.zhang.service.UserManService;
import com.zhang.util.JwtUtil;
import com.zhang.util.RsaUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhang
 * @Description: com.zhang.controller
 * @Date：Created in 13:56 2021/1/6
 */
@RestController
public class UserManController {
    @Value("${defaultPass}")
    private String defaultPass;
    @Resource
    private RsaUtil rsaUtil;
    @Resource
    private UserManService userManService;
    @Resource
    private JwtUtil jwtUtil;
    @PostMapping("userLogin.do")
    public JsonMessage userLogin(String name, String pass) throws Exception {
        JsonMessage jsonMessage = new JsonMessage();
        if(name == null || "".equals(name) || pass == null || "".equals(pass)){
            jsonMessage.setId(0);
            jsonMessage.setMsg("用户名或者密码不为空");
            return jsonMessage;
        }
        pass = rsaUtil.deEncode(pass);

        if( pass == null || "".equals(pass)){
            jsonMessage.setId(0);
            jsonMessage.setMsg("数据出错，请稍后重试");
            return jsonMessage;
        }
        User user = userManService.userLogin(name,DigestUtils.md5Hex(pass));

        if(user == null){
            jsonMessage.setId(-1);
            jsonMessage.setMsg("用户名或密码错误");
        }else{
            jsonMessage.setId(1);
            jsonMessage.setMsg("登陆成功");
            jsonMessage.setLocation("main");
            jsonMessage.getData().put("token",jwtUtil.sign(user.getId()+"",System.currentTimeMillis()));
        }
        return jsonMessage;
    }
    @Auth
    @PostMapping("userGet.do")
    public JsonMessage userGet(String token) throws Exception {
        JsonMessage jsonMessage = new JsonMessage();
        String id = jwtUtil.getClaim(token,"account");
        Long ids = Long.parseLong(id);
        User user = userManService.userGet(ids);
        jsonMessage.setId(1);
        jsonMessage.setMsg("");
        user.setUserPass(DigestUtils.md5Hex(user.getUserPass()));
        jsonMessage.getData().put("user",user);
        return jsonMessage;
    }
    @Auth
    @GetMapping("loginOut.do")
    public JsonMessage userLoginOut() throws Exception {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setId(1);
        jsonMessage.setMsg("退出成功");
        return jsonMessage;
    }
    @Auth
    @PostMapping("userPassMod.do")
    public JsonMessage userPassMod(String token,String newPass,String oldPass) {
        JsonMessage jsonMessage = new JsonMessage();
        Long id = null;
        try {
            id = Long.parseLong(jwtUtil.getClaim(token,"account"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Boolean flag = false;
        try {
            oldPass = rsaUtil.deEncode(oldPass);
            newPass = rsaUtil.deEncode(newPass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            flag = userManService.userModPass(id,DigestUtils.md5Hex(oldPass),DigestUtils.md5Hex(newPass));
        } catch (Exception e) {
            e.printStackTrace();
            jsonMessage.setId(0);
            jsonMessage.setMsg(e.getMessage());
            return jsonMessage;
        }
        if(flag){
            jsonMessage.setId(1);
            jsonMessage.setMsg("修改密码成功,请重新登录");
        }else{
            jsonMessage.setId(0);
            jsonMessage.setMsg("修改密码失败，请稍后重试");
        }
        return jsonMessage;
    }
    @Auth
    @PostMapping("userInfoMod.do")
    public JsonMessage userPassMod(String user,String token) {
        System.err.println("user:"+user);
        User newUser = JSON.parseObject(user,User.class);
        JsonMessage jsonMessage = new JsonMessage();
        try {
            Long id = Long.parseLong(jwtUtil.getClaim(token,"account"));
            newUser.setId(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Boolean flag = false;
        try {
            flag = userManService.userModInfo(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMessage.setId(0);
            jsonMessage.setMsg(e.getMessage());
            return jsonMessage;
        }
        if(flag){
            jsonMessage.setId(1);
            jsonMessage.setMsg("修改信息成功");
        }else{
            jsonMessage.setId(0);
            jsonMessage.setMsg("修改信息失败，请稍后重试");
        }
        return jsonMessage;
    }
    @Auth
    @PostMapping("userDel.do")
    public JsonMessage userDel(Long id){
        JsonMessage jsonMessage = new JsonMessage();
        Boolean flag = false;
        flag = userManService.userDel(id);
        if(flag){
            jsonMessage.setId(1);
            jsonMessage.setMsg("删除成功");
        }else{
            jsonMessage.setMsg("删除失败，请稍后重试");
            jsonMessage.setId(0);
        }
        return jsonMessage;
    }
    @Auth
    @PostMapping("userReset.do")
    public JsonMessage userReset(Long id){
        JsonMessage jsonMessage = new JsonMessage();
        Boolean flag = false;
        flag = userManService.userReset(id);
        if(flag){
            jsonMessage.setId(1);
            jsonMessage.setMsg("重置密码成功");
        }else{
            jsonMessage.setMsg("重置密码失败，请稍后重试");
            jsonMessage.setId(0);
        }
        return jsonMessage;
    }
    @PostMapping("userReg.do")
    public JsonMessage useReg(User user){
        JsonMessage jsonMessage = new JsonMessage();
        if(user.getUserAccount() == null || "".equals(user.getUserAccount())){
            jsonMessage.setId(0);
            jsonMessage.setMsg("用户名不能为空");
            return jsonMessage;
        }
        if(user.getUserPass() == null || "".equals(user.getUserPass())){
            jsonMessage.setId(0);
            jsonMessage.setMsg("密码不能为空");
            return jsonMessage;
        }
        if(user.getUserName() == null || "".equals(user.getUserName())){
            jsonMessage.setId(0);
            jsonMessage.setMsg("用户姓名不能为空");
            return jsonMessage;
        }
        if(user.getUserSex() == null){
            jsonMessage.setId(0);
            jsonMessage.setMsg("用户名性别为空");
            return jsonMessage;
        }
        if(user.getUserEmail() == null || "".equals(user.getUserEmail())){
            jsonMessage.setId(0);
            jsonMessage.setMsg("用户邮箱不能为空");
            return jsonMessage;
        }
        if(user.getUserPhone() == null){
            jsonMessage.setId(0);
            jsonMessage.setMsg("用户手机号不能为空");
            return jsonMessage;
        }
        if(user.getUserAddress() == null || "".equals(user.getUserAddress())){
            jsonMessage.setId(0);
            jsonMessage.setMsg("用户地址不能为空");
            return jsonMessage;
        }
        if(user.getUserBirth() == null){
            jsonMessage.setId(0);
            jsonMessage.setMsg("用户生日不能为空");
            return jsonMessage;
        }
        try {
            user.setUserPass(DigestUtils.md5Hex(rsaUtil.deEncode(user.getUserPass())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Boolean flag = false;
        try {
            flag = userManService.userReg(user);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMessage.setId(0);
            jsonMessage.setMsg(e.getMessage());
            return jsonMessage;
        }
        System.out.println(user);
        if(flag){
            jsonMessage.setMsg("注册成功");
            jsonMessage.setLocation("userLogin");
            jsonMessage.setId(1);
        }else{
            jsonMessage.setMsg("注册失败，请稍后重试");
            jsonMessage.setId(-1);
        }
        return jsonMessage;
    }
    @Auth
    @PostMapping("userMod.do")
    public JsonMessage userMod(String userStr){

        JsonMessage jsonMessage = new JsonMessage();
        User user = JSON.parseObject(userStr,User.class);
        System.err.println("userStr:::"+user);
        Boolean flag = false;
        try {
            flag = userManService.userModInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMessage.setId(0);
            jsonMessage.setMsg(e.getMessage());
            return jsonMessage;
        }
        System.out.println(user);
        if(flag){
            jsonMessage.setMsg("修改成功");
            jsonMessage.setLocation("userLogin");
            jsonMessage.setId(1);
        }else{
            jsonMessage.setMsg("修改失败，请稍后重试");
            jsonMessage.setId(-1);
        }
        return jsonMessage;
    }
    @Auth
    @PostMapping("userAdd.do")
    public JsonMessage useAdd(String userStr){
        JsonMessage jsonMessage = new JsonMessage();
        User user = JSON.parseObject(userStr,User.class);
        user.setUserPass(DigestUtils.md5Hex(defaultPass));
        Boolean flag = false;
        try {
            flag = userManService.userReg(user);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMessage.setId(0);
            jsonMessage.setMsg(e.getMessage());
            return jsonMessage;
        }
        System.out.println(user);
        if(flag){
            jsonMessage.setMsg("添加成功");
            jsonMessage.setLocation("userLogin");
            jsonMessage.setId(1);
        }else{
            jsonMessage.setMsg("添加失败，请稍后重试");
            jsonMessage.setId(-1);
        }
        return jsonMessage;
    }
    @GetMapping("userShow.do")
    public JsonMessage userShow(String account,String phone,String startTime,String endTime,String email,Integer limit,Integer offset,Integer count) throws IOException, ServletException {
        JsonMessage jsonMessage = new JsonMessage();
        List<User> userList = userManService.frontUserShow(account, phone, startTime, endTime, email, offset, limit);
        int counts = -1;
        if (count == 1) {
            counts = userManService.frontUserShowCount(account, phone, startTime, endTime, email);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (userList == null) {
            jsonMessage.setId(0);
            jsonMessage.setMsg("数据出错");
        } else {
            map.put("list", userList);
            map.put("count", counts);
            jsonMessage.setId(1);
            jsonMessage.setData(map);
        }
        return jsonMessage;
    }
}
