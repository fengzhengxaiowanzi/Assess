package com.zhang.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: zhang
 * @Description: com.zhang.pojo
 * @Date：Created in 14:18 2021/1/6
 */
@Data
public class User {
    private Long id;
    private String userAccount;
    private String userPass;
    private String userName;
    private String userSex;
    private String userEmail;
    private Long userPhone;
    private String userAddress;
    private Date userBirth;
}
