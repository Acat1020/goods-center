package com.xw.goodscenter.service;

import com.xw.goodscenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.goodscenter.model.domain.request.UserLoginRequest;
import com.xw.goodscenter.model.domain.request.UserRegisterRequest;

import javax.servlet.http.HttpServletRequest;

/**
* @author 000000
* @description 针对表【user】的数据库操作Service
* @createDate 2022-06-01 17:57:40
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount  用户账号
     * @param userPassword  用户密码
     * @param checkPassword  确认密码
     * @return 新用户ID
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);


    /**
     * 用户登录
     *
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param user 脱敏前的信息
     * @return safetyUser 脱敏后的用户信息
     */
    User getSafetyUser(User user);

    /**
     * 用户注销
     */
    int userLogout(HttpServletRequest request);
}
