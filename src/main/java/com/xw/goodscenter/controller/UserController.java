package com.xw.goodscenter.controller;


import com.xw.goodscenter.model.domain.User;
import com.xw.goodscenter.model.domain.request.UserLoginRequest;
import com.xw.goodscenter.model.domain.request.UserRegisterRequest;
import com.xw.goodscenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.xw.goodscenter.constant.UserConstant.USER_LOGIN_STATE;


/**
 * 用户控制器
 *
 * @author ACat
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求体
     * @return 新用户ID
     */
    @PostMapping("/register")
    public long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);

    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求体
     * @return 脱敏后的用户信息
     */
    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest,HttpServletRequest request){
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return userService.userLogin(userAccount,userPassword,request);
    }

    /**
     * 获取当前登录用户信息
     *
     * @return safetyUser 当前登录用户的信息
     */
    @GetMapping("/currentUser")
    public User currentUser(HttpServletRequest request){
        Object ObjUser = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) ObjUser;
        if (currentUser == null){
            return null;
        }
        Long id = currentUser.getId();
        User user = userService.getById(id);
        return userService.getSafetyUser(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    @DeleteMapping("delete")
    public void deleteUser(@RequestBody Long id){
        if (id<=0){
            return;
        }
        userService.removeById(id);
    }

    /**
     * 用户注销
     *
     * @return 注销成功 1
     */
    @PostMapping("/logout")
    public Integer userLogout(HttpServletRequest request){
        return userService.userLogout(request);
    }
}
